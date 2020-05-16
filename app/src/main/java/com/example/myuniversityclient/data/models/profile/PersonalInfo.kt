package com.example.myuniversityclient.data.models.profile

import java.time.LocalDate

data class PersonalInfo(
    val fullName: String,
    val birthDate: LocalDate,
    val birthPlace: String,
    val sex: String,
    val citizenship: String,
    val snils: String,
    val inn: String,
    val registrationCertificate: String
)