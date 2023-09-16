package org.scesi.mappacino.usescases

import org.koin.core.annotation.Factory
import org.scesi.mappacino.data.SubjectRepository


@Factory
class GetSemestersUseCase(private val semesterRepository: SubjectRepository) {

    operator fun invoke()  = semesterRepository.semesters
}