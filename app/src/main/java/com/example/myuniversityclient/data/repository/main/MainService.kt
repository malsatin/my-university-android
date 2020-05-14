package com.example.myuniversityclient.data.repository.main

import com.example.myuniversityclient.data.models.ShortUserInfo

/**
 * A DAO for retrieving short user info.
 */
interface MainService {
    /**
     * Makes a request for retrieving user info.
     * @param onResult a callback with a result of the request.
     */
    fun getShortUserInfo(onResult: (Result<ShortUserInfo?>) -> Unit)

    /**
     * Fetches auth data
     */
    fun auth(email: String, password: String, onResult:  (Result<Nothing?>) -> Unit)

    /**
     * Clears available auth data.
     */
    fun logout()
}