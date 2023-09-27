package org.scesi.mappacino.data.json

import com.google.gson.annotations.SerializedName
import org.scesi.mappacino.domain.ClassroomLocationUI

data class ClassroomLocationDTO (

    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("facultad")
    var faculty: String? = null,
    @SerializedName("nombre")
    var name: String? = null,
    @SerializedName("descripcion")
    var description: String? = null,
    @SerializedName("latitud")
    var lattitude: Double? = null,
    @SerializedName("longitud")
    var longitude: Double? = null
) 


fun ClassroomLocationDTO.toClassroomLocationUI(): ClassroomLocationUI = ClassroomLocationUI(
    id = this.id?:0 ,
    name = this.name?: "Aula sin nombre",
    faculty = this.faculty?:"Facultad",
    description = this.description?:"Descripcion vacia",
    lattitude = this.lattitude?:0.0,
    longitude = this.longitude?:0.0,
)