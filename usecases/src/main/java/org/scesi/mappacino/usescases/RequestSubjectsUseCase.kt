package org.scesi.mappacino.usescases

import org.koin.core.annotation.Factory
import org.scesi.mappacino.data.SubjectRepository

@Factory
class RequestSubjectsUseCase(private val subjectsRepository: SubjectRepository) {

    suspend operator fun invoke(): Error? {
        return subjectsRepository.requestSubjects()
    }
}