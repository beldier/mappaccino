package org.scesi.mappacino.domain

data class Schedule(
    val day: String,
    val start: String,
    val end: String,
    val duration: Int,
    val room: String,
    val teacher: String,
    val isClass: Boolean
)
