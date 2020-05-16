package com.example.myuniversityclient.data.repository.http

import android.content.SharedPreferences
import android.util.Log
import com.example.myuniversityclient.data.models.AuthMessage
import com.example.myuniversityclient.data.models.InvalidHttpResponse
import com.example.myuniversityclient.data.models.ShortUserInfo
import com.example.myuniversityclient.data.repository.main.MainService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.disposables.Disposable
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject


class HttpClientService {

    private val PORTAL_BASE_URL = "https://my.university.innopolis.ru"
    private val SSO_BASE_URL = "https://sso.university.innopolis.ru:443"
    private var cookies: Map<String, String>?

    @Inject
    lateinit var prefs: SharedPreferences

    init {
        this.cookies = mapOf(
            "_identity" to "4888e5176bf6c0ea1b85f4db26700b97327d41286568c3698c3c2cce563bdbd8a%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22_identity%22%3Bi%3A1%3Bs%3A48%3A%22%5B137%2C%22LF-PrPlzsdyf-iqzEuLasLjJHqKw_mRY%22%2C2592000%5D%22%3B%7D; Expires=Sat, 13-Jun-2020 08:53:07 GMT; Max-Age=2591999; Path=/; HttpOnly"
        )
    }

    fun isAuthenticated(): Boolean {
        return cookies !== null
    }

    fun hasCredentials(): Boolean {
        // todo
        return false
    }

    fun reauth(): AuthMessage {
        // todo
        return AuthMessage("", null, false);
    }

    fun auth(email: String, password: String): AuthMessage {
        val msg = requestAuth(email, password)
        if (msg.isSuccess) {
            // todo: store credentials

            cookies = msg.response!!.cookies()
        }

        return msg
    }

    fun logout() {
        // todo: clear credentials

        cookies = null
    }

    fun requestAuth(email: String, password: String): AuthMessage {
        val doc = Jsoup.connect("$PORTAL_BASE_URL/site/auth?authclient=adfs").get()
        val form = doc.getElementById("loginForm")
        val action = SSO_BASE_URL + form.attr("action")

        val response = Jsoup.connect(action).method(Connection.Method.POST)
            .data("UserName", email)
            .data("Password", password)
            .data("Kmsi", "1")
            .data("AuthMethod", "FormsAuthentication")
            .followRedirects(true)
            .execute()

        val responseDoc = response.parse()
        val responseError = responseDoc.getElementById("errorText")
        if (responseError !== null) {
            val errorText = responseError.text()
            if (errorText !== null && errorText.isNotEmpty()) {
                return AuthMessage(errorText, response,false)
            }
        }

        return AuthMessage("Authorized!", response, true)
    }

    fun requestUserInfo(): ShortUserInfo {
        val doc = requestPage("$PORTAL_BASE_URL/profile")

        val profileBlock = doc.getElementsByClass("card-profile").first()
        val navBar = doc.getElementsByClass("navbar-nav").first()
        val navItems = navBar.getElementsByTag("li")

        val cardAvatar = profileBlock.getElementsByClass("card-avatar").first()
        val cardBody = profileBlock.getElementsByClass("card-body").first()

        val avatarEl = cardAvatar.getElementsByTag("img").first()
        val nameEl = cardBody.getElementsByClass("card-title").first()
        val emailEl = navItems[1].getElementsByTag("a").first().getElementsByTag("b")

        if (avatarEl == null || nameEl == null || emailEl == null) {
            throw InvalidHttpResponse("Failed to find one of elements")
        }

        return ShortUserInfo(
            PORTAL_BASE_URL + "/" + avatarEl.attr("src"),
            nameEl.text(),
            emailEl.text()
        )
    }

    private fun requestPage(path: String): Document {
        return Jsoup.connect(path)
            .method(Connection.Method.GET).cookies(this.cookies)
            .execute().parse()
    }
}