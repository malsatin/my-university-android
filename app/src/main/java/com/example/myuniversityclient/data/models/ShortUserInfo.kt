package com.example.myuniversityclient.data.models

/**
 * A short info about a user.
 * Used for displaying essential info about current user in drawer header.
 */
data class ShortUserInfo(
    val avatarURL: String,
    val name: String,
    val email: String
)