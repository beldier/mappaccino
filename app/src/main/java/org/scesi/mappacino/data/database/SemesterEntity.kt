package org.scesi.mappacino.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class SemesterEntity(
    @PrimaryKey
    val semesterCode: String ,
)

data class SemesterWithSubjects(
    @Embedded val semester: SemesterEntity,
    @Relation(
        parentColumn = "semesterCode",
        entityColumn = "parentSemesterCode"
    )
    val subjects: List<SubjectEntity>
)
