package org.scesi.mappacino.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    foreignKeys = [ForeignKey(
        entity = SubjectEntity::class,
        parentColumns = ["subjectCode"],
        childColumns = ["parentSubjectCode"],
        onDelete = CASCADE
    )]
)
data class GroupEntity(
    @PrimaryKey
    val id:String,
    val groupCode: String,
    val teacher: String,
    val parentSubjectCode: String,
)

data class GroupWithSchedule(
    @Embedded val groupEntity: GroupEntity,
    @Relation(
        parentColumn = "groupCode",
        entityColumn = "parentGroupCode",
        entity = ScheduleEntity::class
    )
    val schedules: List<ScheduleEntity>
)