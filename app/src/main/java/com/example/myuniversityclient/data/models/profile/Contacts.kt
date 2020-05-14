package com.example.myuniversityclient.data.models.profile

data class Contacts(
    val registrationAddress: String,
    val residenceAddress: String,
    val emails: List<String>,
    val telegramsAliases: List<String>,
    val phones: List<String>
)