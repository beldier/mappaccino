package org.scesi.mappacino.domain

data class GroupUI(
    val code: String,
    var schedule: List<ScheduleUI> = listOf(),
    val teacher: String,
    val id:String,
)
