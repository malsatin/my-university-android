package com.example.myuniversityclient.data.repository.http

import android.content.SharedPreferences
import com.example.myuniversityclient.data.models.ShortUserInfo
import com.example.myuniversityclient.data.repository.main.MainService
import javax.inject.Inject

class HttpClientService @Inject constructor(
    private val prefs: SharedPreferences? = null
) : MainService {

    private val cookies: Map<String, String>

    init {
        this.cookies = HashMap()
    }

    override fun getShortUserInfo(onResult: (Result<ShortUserInfo?>) -> Unit) {
        // todo
        // send http request to https://my.university.innopolis.ru/profile
        // parse HTML
        // avatar = "https://my.university.innopolis.ru/" + `.card-profile .card-avatar img` -> src
        // name = `.card-profile .card-body .card-title` -> text
        // email = `.nav.navbar-nav li:gt(2) > a b` -> text

        //onResult(Result.success(ShortUserInfo()))
    }

    override fun auth(email: String, password: String, onResult: (Result<Nothing?>) -> Unit) {
        // todo
        // request https://my.university.innopolis.ru/site/auth?authclient=adfs
        // store PHPSESSID and _identity cookies
        // find #loginForm
        // get action from it
        /*
        send
        {
        UserName: email
        Password: password
        Kmsi: 1
        AuthMethod: FormsAuthentication
        } to this action
         */
        // update PHPSESSID and _identity cookies

        onResult(Result.success(null))
    }

    override fun logout() {
        // todo
        // flush PHPSESSID and _identity cookies (all cookies)
    }

    private fun httpRequestPage(url: String, onResult: (Result<String>) -> Unit) {
        // todo
        // send request to url with stored cookies

        //onResult(Result.success(""))
    }
}