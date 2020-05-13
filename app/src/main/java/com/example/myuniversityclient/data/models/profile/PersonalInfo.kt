package com.example.myuniversityclient.data.models.profile

import java.util.*

data class PersonalInfo(
    val fullName: String,
    val birthDate: Date,
    val sex: String,
    val citizenship: String,
    val snils: String,
    val inn: String,
    val registrationCertificate: String
)