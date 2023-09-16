package org.scesi.mappacino.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface SubjectDao {

    @Query("SELECT * FROM SemesterEntity")
    fun getAll(): Flow<List<SemesterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSemesters(semesters: List<SemesterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(subjects: List<SubjectEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroups(groups: List<GroupEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedules(schedules: List<ScheduleEntity>)

    @Transaction
    @Query("SELECT * FROM SubjectEntity WHERE parentSemesterCode = :semesterCode")
    suspend fun getSubjectBySemester(semesterCode:String): List<SubjectEntity>

    @Transaction
    @Query("SELECT * FROM GroupEntity WHERE parentSubjectCode = :subjectCode")
    suspend fun getGroupsBySubject(subjectCode :String): List<GroupEntity>

    @Transaction
    @Query("SELECT * FROM ScheduleEntity WHERE parentGroupCode = :groupCode")
    suspend fun getSchedulesByGroup(groupCode :String): List<ScheduleEntity>

}