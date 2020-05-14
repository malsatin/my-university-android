package com.example.myuniversityclient.data.repository.http

import android.content.SharedPreferences
import com.example.myuniversityclient.data.models.ShortUserInfo
import com.example.myuniversityclient.data.repository.main.MainService
import javax.inject.Inject

class HttpClientService @Inject constructor(
    private val prefs: SharedPreferences? = null
) : MainService {

    override fun getShortUserInfo(onResult: (Result<ShortUserInfo?>) -> Unit) {
        // todo

        //onResult(Result.success(ShortUserInfo()))
    }

    override fun auth(email: String, password: String, onResult: (Result<Nothing?>) -> Unit) {
        // todo

        onResult(Result.success(null))
    }

    override fun logout() {
    }
}