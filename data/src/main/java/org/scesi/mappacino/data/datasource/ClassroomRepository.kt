package org.scesi.mappacino.data.datasource

import org.koin.core.annotation.Factory
import org.scesi.mappacino.domain.ClassroomLocationUI

@Factory
class ClassroomRepository(
    private val classRoomLocalDataSource: ClassRoomLocalDataSource
) {

    suspend fun getClassroomLocation(query: String): List<ClassroomLocationUI> {
        val locations = classRoomLocalDataSource.getClassroomsLocations()
        return locations?.filter { it.id.toString().contains(query)|| it.name.contains(query)  } ?: listOf()
    }
}