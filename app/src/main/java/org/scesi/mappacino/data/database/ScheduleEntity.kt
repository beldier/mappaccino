package org.scesi.mappacino.data.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = GroupEntity::class,
        parentColumns = ["id"],
        childColumns = ["parentGroupCode"],
        onDelete = CASCADE
    )]
)
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val scheduleCode: Int = 0,
    val parentGroupCode: String,
    val day: String? = null,
    val start: String? = null,
    val end: String? = null,
    val duration: Int? = null,
    val room: String? = null,
    val isClass: Boolean? = null,
    val teacher: String? = null
)