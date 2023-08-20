package org.scesi.mappacino.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import org.scesi.mappacino.data.datasource.SubjectLocalDataSource
import org.scesi.mappacino.domain.Subject

@Factory
class SubjectRoomDataSource(): SubjectLocalDataSource {
    override val subjects: Flow<List<Subject>>
        get() = flowOf(listOf())

    override suspend fun isEmpty(): Boolean {
        return false
    }

    override fun findById(id: Int): Flow<Subject> {
        return flowOf(Subject("","", listOf()))
    }

    override suspend fun save(subjects: List<Subject>): Error? {
        return null
    }
}