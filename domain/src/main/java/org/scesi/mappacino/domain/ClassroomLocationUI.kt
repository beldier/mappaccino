package org.scesi.mappacino.domain

data class ClassroomLocationUI(
    var id: Int,
    var name: String,
    var faculty: String, // TODO maybe i can use an enum here, review repository
    var description: String,
    var lattitude: Double,
    var longitude: Double,
)
