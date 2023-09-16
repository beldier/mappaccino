package org.scesi.mappacino.data.server.models

import org.scesi.mappacino.domain.SubjectUI

data class SemesterServer (
    val code: String = "",
    val subjects: List<SubjectUI> = listOf()
)