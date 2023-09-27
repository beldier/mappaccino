package org.scesi.mappacino.domain

data class SemesterUI (
    val code: String = "",
    val name: String = "",
    val subjects: List<SubjectUI> = listOf()
)