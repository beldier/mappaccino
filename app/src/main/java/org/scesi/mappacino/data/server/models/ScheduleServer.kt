package org.scesi.mappacino.data.server.models

data class ScheduleServer(
    val day: String,
    val start: String,
    val end: String,
    val duration: Int,
    val room: String,
    val teacher: String,
    val isClass: Boolean
)