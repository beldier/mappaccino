package org.scesi.mappacino.data.json

import android.content.Context
import org.koin.core.annotation.Factory
import org.scesi.mappacino.data.datasource.ClassRoomLocalDataSource
import org.scesi.mappacino.data.toParsedList
import org.scesi.mappacino.domain.ClassroomLocationUI




private const val JSON_FILE_NAME = "coordinates.json"
@Factory
class ClassroomLocationJsonDataSource(private val applicationContext : Context) : ClassRoomLocalDataSource {
    override suspend fun getClassroomsLocations(): List<ClassroomLocationUI>? {
        return try {
            val fileInString: String =
                applicationContext.assets.open(JSON_FILE_NAME).bufferedReader().use { it.readText() }
            val parsedList = fileInString.toParsedList<ClassroomLocationDTO>()
            parsedList.map { it.toClassroomLocationUI() }
        } catch (e:Exception){
            e.printStackTrace()
            null
        }

    }
}