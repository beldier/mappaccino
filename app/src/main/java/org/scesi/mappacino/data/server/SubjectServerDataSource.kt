package org.scesi.mappacino.data.server

import arrow.core.Either
import arrow.core.right
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import org.scesi.mappacino.data.datasource.SubjectRemoteDataSource
import org.scesi.mappacino.domain.Subject

@Factory
class SubjectServerDataSource() : SubjectRemoteDataSource {
    override suspend fun findSubjects(): Either<Error, List<Subject>> {
        val a  = RemoteConnection.service.getComputerScienceSubjects()
        a.code

        return listOf<Subject>().right()
    }

}
//
//private fun List<RemoteMovie>.toDomainModel(): List<Movie> = map { it.toDomainModel() }
//
//
//private fun RemoteMovie.toDomainModel(): Movie = Movie(
//    id,
//    title,
//    overview,
//    releaseDate,
//    "https://image.tmdb.org/t/p/w185/$posterPath",
//    backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" } ?: "",
//    originalLanguage,
//    originalTitle,
//    popularity,
//    voteAverage,
//    false
//)