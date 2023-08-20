package org.scesi.mappacino.data.datasource

import arrow.core.Either
import org.scesi.mappacino.domain.Subject

interface SubjectRemoteDataSource {
    suspend fun findSubjects(): Either<Error, List<Subject>>
}