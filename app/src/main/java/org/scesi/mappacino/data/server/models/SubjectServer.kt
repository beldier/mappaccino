package org.scesi.mappacino.data.server.models

data class SubjectServer (
    val code: String,
    val semesterCode: String,
    val name: String,
    val groups: List<GroupsServer> = listOf()
)


