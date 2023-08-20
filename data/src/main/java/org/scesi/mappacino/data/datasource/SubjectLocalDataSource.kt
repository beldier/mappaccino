package org.scesi.mappacino.data.datasource

import kotlinx.coroutines.flow.Flow
import org.scesi.mappacino.domain.Subject

interface SubjectLocalDataSource {
    val subjects: Flow<List<Subject>>
    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<Subject>
    suspend fun save(subjects: List<Subject>): Error?
}