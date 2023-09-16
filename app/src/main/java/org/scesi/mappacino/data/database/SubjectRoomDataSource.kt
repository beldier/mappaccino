package org.scesi.mappacino.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import org.scesi.mappacino.data.datasource.SubjectLocalDataSource
import org.scesi.mappacino.data.tryCall
import org.scesi.mappacino.domain.GroupUI
import org.scesi.mappacino.domain.ScheduleUI
import org.scesi.mappacino.domain.SubjectUI
import org.scesi.mappacino.domain.Error
import org.scesi.mappacino.domain.SemesterUI

@Factory
class SubjectRoomDataSource(private val subjectDao: SubjectDao) : SubjectLocalDataSource {

    override val semesters: Flow<List<SemesterUI>> = subjectDao.getAll().map { semesters ->
        semesters.map { semester ->
            val subjects = subjectDao.getSubjectBySemester(semester.semesterCode).toSubjectDomainModel()
            subjects.forEach { subject ->
                val groups = subjectDao.getGroupsBySubject(subject.code)
                subject.groups = groups.toGroupDomainModel()
                subject.groups.forEach { group ->
                    group.schedule = subjectDao.getSchedulesByGroup(group.id).toScheduleDomainModel()
                }
            }
            SemesterUI(
                code = semester.semesterCode,
                name = semester.semesterCode,
                subjects = subjects
            )
        }
    }

    override suspend fun isEmpty(): Boolean {
        return true // todo implement
    }

    override fun findById(id: Int): Flow<SubjectUI> {
        return flowOf(SubjectUI("", "", listOf()))
    }

    override suspend fun save(semesters: List<SemesterUI>): Error? = tryCall {

        subjectDao.insertSemesters(semesters = semesters.toEntityModel1())
        semesters.forEach { semester ->
            subjectDao.insertSubjects(subjects = semester.subjects.toEntityModel2(semester.code))
            semester.subjects.forEach { subject ->
                subjectDao.insertGroups(subject.groups.toEntityModel(subject.code))
                subject.groups.forEach { group ->
                    subjectDao.insertSchedules(group.schedule.toEntityModel3(group.id))
                }
            }
        }
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

}


private fun List<SubjectEntity>.toSubjectDomainModel(): List<SubjectUI> = map { it.toDomainModel() }

private fun SubjectEntity.toDomainModel(): SubjectUI =
    SubjectUI(code = this.subjectCode, name = this.name, groups = listOf())

private fun List<GroupEntity>.toGroupDomainModel(): List<GroupUI> = map { it.toDomainModel() }

private fun GroupEntity.toDomainModel(): GroupUI =
    GroupUI(
        code = this.groupCode,
        schedule = listOf(),
        teacher = this.teacher,
        id = this.id
    )

private fun List<ScheduleEntity>.toScheduleDomainModel(): List<ScheduleUI> = map { it.toDomainModel() }


private fun ScheduleEntity.toDomainModel(): ScheduleUI = ScheduleUI(
    day = this.day ?: "",
    start = this.start ?: "",
    end = this.end ?: "",
    duration = this.duration ?: 0,
    room = this.room ?: "",
    isClass = this.isClass ?: false,
    teacher = this.teacher ?: "",
)

////////////


private fun List<SemesterUI>.toEntityModel1(): List<SemesterEntity> = map { it.toEntityModel() }

private fun SemesterUI.toEntityModel(): SemesterEntity = SemesterEntity(
    semesterCode = this.code
)

private fun List<SubjectUI>.toEntityModel2(semesterCode: String): List<SubjectEntity> =
    map { it.toEntityModel(semesterCode) }

private fun SubjectUI.toEntityModel(semesterCode: String): SubjectEntity =
    SubjectEntity(
        subjectCode = this.code,
        name = this.name,
        parentSemesterCode = semesterCode
    )

private fun List<GroupUI>.toEntityModel(subjectCode: String): List<GroupEntity> =
    map { it.toEntityModel(subjectCode) }

private fun GroupUI.toEntityModel(subjectCode: String): GroupEntity =
    GroupEntity(
        groupCode = this.code,
        teacher = this.teacher,
        parentSubjectCode = subjectCode,
        id = "${this.code}${subjectCode}"
    )

private fun List<ScheduleUI>.toEntityModel3(generateIdGroupCode: String): List<ScheduleEntity> =
    map { it.toEntityModel(generateIdGroupCode) }

private fun ScheduleUI.toEntityModel(generateIdGroupCode: String): ScheduleEntity = ScheduleEntity(
    day = this.day,
    start = this.start,
    end = this.end,
    duration = this.duration,
    room = this.room,
    isClass = this.isClass,
    teacher = this.teacher,
    parentGroupCode = generateIdGroupCode
)