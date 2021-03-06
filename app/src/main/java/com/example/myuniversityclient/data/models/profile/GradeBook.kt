package com.example.myuniversityclient.data.models.profile

data class GradeBook(
    val id: String,
    val grades: List<Mark>
) {
    data class Mark(
        val discipline: String,
        val teacher: String,
        val mark: String
    )
}