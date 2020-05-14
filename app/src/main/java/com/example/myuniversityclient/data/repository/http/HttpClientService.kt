package com.example.myuniversityclient.data.repository.http

import android.content.SharedPreferences
import com.example.myuniversityclient.data.models.AuthMessage
import com.example.myuniversityclient.data.models.InvalidHttpResponse
import com.example.myuniversityclient.data.models.ShortUserInfo
import com.example.myuniversityclient.data.repository.main.MainService
import org.jsoup.Connection
import org.jsoup.Jsoup
import javax.inject.Inject

class HttpClientService @Inject constructor(
    private val prefs: SharedPreferences? = null
) : MainService {

    private val PORTAL_BASE_URL = "https://my.university.innopolis.ru"
    private val SSO_BASE_URL = "https://sso.university.innopolis.ru:443"
    private var cookies: Map<String, String>?

    init {
        this.cookies = HashMap()
    }

    override fun getShortUserInfo(onResult: (Result<ShortUserInfo?>) -> Unit) {
        val thread = Thread(Runnable {
            val doc = Jsoup.connect("$PORTAL_BASE_URL/profile").get()

            var userInfo: ShortUserInfo? = null
            try {
                val profileBlock = doc.getElementsByClass("card-profile").first()
                val navBar = doc.getElementsByClass("nav.navbar-nav").first()
                val navItems = navBar.getElementsByTag("li")

                val cardAvatar = profileBlock.getElementsByClass("card-avatar").first()
                val cardBody = profileBlock.getElementsByClass("card-body").first()

                val avatarEl = cardAvatar.getElementsByTag("img").first()
                val nameEl = cardBody.getElementsByClass("card-title").first()
                val emailEl = navItems[2].getElementsByTag("a").first().getElementsByTag("b")

                if (avatarEl == null || nameEl == null || emailEl == null) {
                    throw InvalidHttpResponse("Failed to find one of elements")
                }

                userInfo = ShortUserInfo(
                    PORTAL_BASE_URL + "/" + avatarEl.attr("src"),
                    nameEl.text(),
                    emailEl.text()
                )
            } catch (e: Throwable) {
                onResult(Result.failure(e))
            }

            onResult(Result.success(userInfo))
        })
        thread.start()
    }

    override fun auth(email: String, password: String, onResult: (Result<AuthMessage?>) -> Unit) {
        val thread = Thread(Runnable {
            try {
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
                        onResult(Result.success(AuthMessage(errorText, false)))
                        return@Runnable
                    }
                }

                cookies = response.cookies()
            } catch (e: Throwable) {
                onResult(Result.failure(e))
            }

            onResult(Result.success(AuthMessage("Authorized!")))
        })
        thread.start()
    }

    override fun logout() {
        cookies = null
    }
}