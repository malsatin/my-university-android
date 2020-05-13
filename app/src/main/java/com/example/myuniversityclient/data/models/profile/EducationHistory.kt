package com.example.myuniversityclient.data.models.profile

import java.util.*

data class EducationHistory(
    val educationYears: List<EducationYear>
) {
    data class EducationYear(
        val startDate: Date,
        val speciality: String,
        val course: String,
        val group: String,
        val academicYear: String,
        val Status: String
    )
}