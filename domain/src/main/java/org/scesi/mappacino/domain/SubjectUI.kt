package org.scesi.mappacino.domain

data class SubjectUI(
    val code: String,
    val name: String,
    var groups: List<GroupUI> = listOf()
)