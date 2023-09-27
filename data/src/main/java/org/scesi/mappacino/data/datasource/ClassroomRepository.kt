package org.scesi.mappacino.data.datasource

import org.koin.core.annotation.Factory
import org.scesi.mappacino.domain.ClassroomLocationUI

@Factory
class ClassroomRepository(
    private val classRoomLocalDataSource: ClassRoomLocalDataSource
) {

    suspend fun getClassroomLocation(query: String): ClassroomLocationUI? {
        val locations = classRoomLocalDataSource.getClassroomsLocations()
        return locations?.firstOrNull { it.id.toString() == query || it.name == query }
    }
}