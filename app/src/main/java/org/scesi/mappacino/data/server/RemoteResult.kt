package org.scesi.mappacino.data.server

import com.google.gson.annotations.SerializedName

data class RemoteResult(
    @SerializedName("madeIn")
    val madeIn: String? = null,
    @SerializedName("semester")
    val semester: String? = null,
    @SerializedName("support")
    val support: String? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("levels")
    val levels: List<Levels> = listOf()
)

data class Levels(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("subjects")
    val subjects: List<Subjects> = listOf()
)

data class Subjects(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("groups")
    val groups: List<Groups> = listOf()
)

data class Groups(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("schedule")
    val schedule: List<Schedule> = listOf(),
    @SerializedName("teacher")
    val teacher: String? = null
)

data class Schedule(
    @SerializedName("day")
    val day: String? = null,
    @SerializedName("start")
    val start: String? = null,
    @SerializedName("end")
    val end: String? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("room")
    val room: String? = null,
    @SerializedName("teacher")
    val teacher: String? = null,
    @SerializedName("isClass")
    val isClass: Boolean? = null
)
