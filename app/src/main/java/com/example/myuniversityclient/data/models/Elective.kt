package com.example.myuniversityclient.data.models

import java.time.Instant

/**
 * Information about an elective in an electives list.
 */
data class Elective(
    val subscriptionDate: Instant,
    val type: String,
    val name: String
)