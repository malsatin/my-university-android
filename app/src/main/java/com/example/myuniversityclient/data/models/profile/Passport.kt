package com.example.myuniversityclient.data.models.profile

import java.util.*

data class Passport (
    val passportSeries: String,
    val number: String,
    val dateOfIssue: Date,
    val authorityCode: String
)