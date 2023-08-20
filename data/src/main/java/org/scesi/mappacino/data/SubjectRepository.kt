package org.scesi.mappacino.data

import org.koin.core.annotation.Factory
import org.scesi.mappacino.data.datasource.SubjectLocalDataSource
import org.scesi.mappacino.data.datasource.SubjectRemoteDataSource

@Factory
class SubjectRepository(
    private val remoteDataSource: SubjectRemoteDataSource,
    private val localDataSource: SubjectLocalDataSource
) {

    val subjects = localDataSource.subjects

    suspend fun requestSubjects(): Error? {
        val movies = remoteDataSource.findSubjects()
        if (localDataSource.isEmpty()) {

            movies.fold(ifLeft = { return it }) {
                // TODO save in database
//                localDataSource.save(it)
            }
        }
        return null
    }

}