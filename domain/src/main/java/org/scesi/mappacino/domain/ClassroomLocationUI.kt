package org.scesi.mappacino.domain

data class ClassroomLocationUI(
    var id: Int,
    override var name: String,
    var faculty: String, // TODO maybe i can use an enum here, review repository
    var description: String,
    var latitude: Double,
    var longitude: Double,
) : ISearchable
