package org.scesi.mappacino.data

import org.koin.core.annotation.Factory
import org.scesi.mappacino.data.datasource.SubjectLocalDataSource
import org.scesi.mappacino.data.datasource.SubjectRemoteDataSource
import org.scesi.mappacino.domain.Error

@Factory
class SubjectRepository(
    private val remoteDataSource: SubjectRemoteDataSource,
    private val localDataSource: SubjectLocalDataSource
) {

    val semesters = localDataSource.semesters

    suspend fun requestSubjects(): Error? {
//        if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.findSubjects()
            movies.fold(ifLeft = { return it }) {
                localDataSource.save(it)
            }
//        }
        return null
    }

}