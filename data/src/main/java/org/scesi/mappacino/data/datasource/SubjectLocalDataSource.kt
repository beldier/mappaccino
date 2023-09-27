package org.scesi.mappacino.data.datasource

import kotlinx.coroutines.flow.Flow
import org.scesi.mappacino.domain.SubjectUI
import org.scesi.mappacino.domain.Error
import org.scesi.mappacino.domain.SemesterUI

interface SubjectLocalDataSource {
    val semesters: Flow<List<SemesterUI>>
    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<SubjectUI>
    suspend fun save(subjects: List<SemesterUI>): Error?
}