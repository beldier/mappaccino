package org.scesi.mappacino.data.server.models

import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation
import org.scesi.mappacino.data.database.SubjectEntity

data class GroupsServer(
    @PrimaryKey
    val code: String,
    val subjectCode: String,
    val schedule: List<ScheduleServer> = listOf(),
    val teacher: String
)

data class SemesterWithSubjects(
    @Embedded val subject: SubjectEntity,
    @Relation(
        parentColumn = "code",
        entityColumn = "subjectCode"
    )
    val subjects: List<GroupsServer>

)