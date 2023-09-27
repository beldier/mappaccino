package org.scesi.mappacino.usescases

import org.koin.core.annotation.Factory
import org.scesi.mappacino.data.datasource.ClassroomRepository
import org.scesi.mappacino.domain.ClassroomLocationUI


@Factory
class GetClassRoomCoordinateUseCase(private val classroomRepository: ClassroomRepository) {
    suspend operator fun invoke(query: String): ClassroomLocationUI? =
        classroomRepository.getClassroomLocation(query)
}