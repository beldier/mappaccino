package org.scesi.mappacino.data.server

import arrow.core.Either
import arrow.core.right
import org.koin.core.annotation.Factory
import org.scesi.mappacino.data.datasource.SubjectRemoteDataSource
import org.scesi.mappacino.domain.Error
import org.scesi.mappacino.domain.GroupUI
import org.scesi.mappacino.domain.ScheduleUI
import org.scesi.mappacino.domain.SemesterUI
import org.scesi.mappacino.domain.SubjectUI

@Factory
class SubjectServerDataSource() : SubjectRemoteDataSource {
    override suspend fun findSubjects(): Either<Error, List<SemesterUI>> {
        val a = RemoteConnection.service.getComputerScienceSubjects()
        return a.levels.toDomainModel1().right()
    }

}


private fun List<Levels>.toDomainModel1(): List<SemesterUI> = map { it.toDomainModel() }

private fun Levels.toDomainModel(): SemesterUI = SemesterUI(
    code = this.code ?: "", subjects = this.subjects.toDomainModel2()
)

private fun List<Subjects>.toDomainModel2(): List<SubjectUI> = map { it.toDomainModel() }

private fun Subjects.toDomainModel(): SubjectUI = SubjectUI(
    code = this.code ?: "", name = this.name ?: "", groups = this.groups.toDomainModel3(this.code?:"")
)

private fun List<Groups>.toDomainModel3(subjectCode: String): List<GroupUI> = map { it.toDomainModel(subjectCode)}

private fun Groups.toDomainModel(subjectCode: String): GroupUI = GroupUI(
    code = this.code ?: "",
    schedule = this.schedule.toDomainModel4(),
    teacher = this.teacher ?: "",
    id = "${this.code}${subjectCode}"
)

private fun List<Schedule>.toDomainModel4(): List<ScheduleUI> = map { it.toDomainModel() }

private fun Schedule.toDomainModel() = ScheduleUI(
    day = this.day ?: "",
    start = this.start ?: "",
    end = this.end ?: "",
    duration = this.duration ?: 0,
    room = this.room ?: "",
    teacher = this.teacher ?: "",
    isClass = this.isClass ?: false
)
