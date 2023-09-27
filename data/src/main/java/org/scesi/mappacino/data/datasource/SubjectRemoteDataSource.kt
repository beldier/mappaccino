package org.scesi.mappacino.data.datasource

import arrow.core.Either
import org.scesi.mappacino.domain.Error
import org.scesi.mappacino.domain.SemesterUI

interface SubjectRemoteDataSource {
    suspend fun findSubjects(): Either<Error, List<SemesterUI>>
}