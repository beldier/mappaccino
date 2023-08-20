package org.scesi.mappacino.domain

data class Group(
    val code: String,
    val schedule: List<Schedule> = listOf(),
    val teacher: String
)
