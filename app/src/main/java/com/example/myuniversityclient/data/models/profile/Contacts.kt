package com.example.myuniversityclient.data.models.profile

import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone

data class Contacts (
    val registrationAddress: String,
    val residenceAddress: String,
    val emails: List<Email>,
    val telegramsAliases: List<String>,
    val phones: List<Phone>
)