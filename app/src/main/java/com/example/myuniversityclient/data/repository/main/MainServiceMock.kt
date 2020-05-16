package com.example.myuniversityclient.data.repository.main

import com.example.myuniversityclient.data.models.AuthMessage
import com.example.myuniversityclient.data.models.ShortUserInfo

/**
 * Temporary backend substitution.
 */
class MainServiceMock : MainService {
    override fun getShortUserInfo(onResult: (Result<ShortUserInfo?>) -> Unit) {
        val mockInfo = ShortUserInfo(
            "https://my.university.innopolis.ru/persons/091a512f-5972-11e6-812e-00155d640253.jpg",
            "Khabirov Bulat",
            "b.khabirov@innopolis.ru"
        )

        onResult(Result.success(mockInfo))
    }

    override fun auth(email: String, password: String, onResult: (Result<AuthMessage?>) -> Unit) {
        onResult(Result.success(null))
    }

    override fun logout() {}
}