package org.scesi.mappacino.domain

data class Subject(
    val code: String,
    val name: String,
    val groups: List<Group> = listOf()
)