package org.scesi.mappacino.data.datasource

import org.scesi.mappacino.domain.ClassroomLocationUI

interface ClassRoomLocalDataSource {

    suspend fun getClassroomsLocations(): List<ClassroomLocationUI>?

}