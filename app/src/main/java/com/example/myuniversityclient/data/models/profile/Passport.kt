package com.example.myuniversityclient.data.models.profile

import java.time.LocalDate

data class Passport(
    val passportSeries: String,
    val number: String,
    val dateOfIssue: LocalDate,
    val authorityCode: String
)