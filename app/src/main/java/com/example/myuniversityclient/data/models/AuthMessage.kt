package com.example.myuniversityclient.data.models

import org.jsoup.Connection

data class AuthMessage(
    val msg: String,
    val response: Connection.Response?,
    val isSuccess: Boolean = true
)
