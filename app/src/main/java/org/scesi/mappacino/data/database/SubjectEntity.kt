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
        entity = SemesterEntity::class,
        parentColumns = ["semesterCode"],
        childColumns = ["parentSemesterCode"],
        onDelete = CASCADE

    )]
)
data class SubjectEntity(
    @PrimaryKey
    val subjectCode: String,
    val name: String,
    val parentSemesterCode: String,
)


data class SubjectWithGroups(
    @Embedded val subject: SubjectEntity,
    @Relation(
        parentColumn = "subjectCode",
        entityColumn = "parentSubjectCode"
    )
    val groups: List<GroupEntity>

)