package org.scesi.mappacino.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SemesterEntity::class, SubjectEntity::class, ScheduleEntity::class, GroupEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CampusDatabase : RoomDatabase() {

    abstract fun subjectDao(): SubjectDao
}