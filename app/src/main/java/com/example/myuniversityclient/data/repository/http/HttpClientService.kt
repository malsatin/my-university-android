package com.example.myuniversityclient.data.repository.http

import android.content.SharedPreferences
import com.example.myuniversityclient.data.models.*
import com.example.myuniversityclient.data.models.profile.*
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*
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
                return AuthMessage(errorText, response, false)
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

    fun requestProfileContacts(): Contacts {
        val doc = requestPage("$PORTAL_BASE_URL/profile/personal-form/index?tab=contacts")

        val table = doc.getElementsByClass("card-content")[0].getElementsByTag("tbody")[0]
        val tRows = table.getElementsByTag("tr")

        fun findRowValues(name: String): List<String> {
            val list = LinkedList<String>()
            tRows.forEach {
                val cells = it.getElementsByTag("td")
                if (cells[0].text().trim() == name) {
                    list.add(cells[1].text().trim())
                }
            }
            if (list.isEmpty()) {
                list.add("Not found")
            }

            return list
        }

        return Contacts(
            findRowValues("Registration address")[0],
            findRowValues("Residence address")[0],
            findRowValues("E-mail"),
            findRowValues("Telegram"),
            findRowValues("Phone")
        )
    }

    fun requestProfileEducationHistory(): EducationHistory {
        val datePattern = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val doc = requestPage("$PORTAL_BASE_URL/profile/personal-form/index?tab=education")

        val table = doc.getElementsByClass("card-content")[0].getElementsByTag("tbody")[0]
        val tRows = table.getElementsByTag("tr")

        return EducationHistory(tRows.map {
            val cells = it.getElementsByTag("td")

            return@map EducationHistory.EducationYear(
                LocalDate.parse(cells[0].text(), datePattern),
                cells[1].text(),
                cells[2].text(),
                cells[3].text(),
                cells[4].text(),
                cells[5].text()
            )
        })
    }

    fun requestProfileGradeBook(): GradeBook {
        val doc = requestPage("$PORTAL_BASE_URL/profile/personal-form/index?tab=validations")

        val table = doc.getElementsByClass("card-content")[0]
        val tHead = table.getElementsByTag("thead")[0]
        val tBody = table.getElementsByTag("tbody")[0]

        var studentID = tHead.getElementsByTag("h5")[0].text()
        if (studentID.isNotEmpty()) {
            studentID = studentID.split(":")[1].trim()
        }

        val tRows = tBody.getElementsByTag("tr")
        val marksList = tRows.map {
            val cells = it.getElementsByTag("td")

            return@map GradeBook.Mark(
                cells[0].text(),
                cells[1].text(),
                cells[2].text()
            )
        }

        return GradeBook(studentID, marksList)
    }

    fun requestProfilePassport(): PassportData {
        val datePattern = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val doc = requestPage("$PORTAL_BASE_URL/profile/personal-form/index?tab=passport")

        val table = doc.getElementsByClass("card-content")[0]
        val tRows = table.getElementsByClass("row")

        return PassportData(tRows.map {
            val inputs = it.getElementsByTag("input")

            return@map Passport(
                inputs[0].attr("value"),
                inputs[1].attr("value"),
                LocalDate.parse(inputs[2].attr("value"), datePattern),
                inputs[3].attr("value")
            )
        })
    }

    fun requestProfilePersonalInfo(): PersonalInfo {
        val datePattern = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val doc = requestPage("$PORTAL_BASE_URL/profile/personal-form/index?tab=person")

        val table = doc.getElementsByClass("card-content")[0]
        val inputs = table.getElementsByTag("input")

        return PersonalInfo(
            inputs[0].attr("value"),
            LocalDate.parse(inputs[1].attr("value"), datePattern),
            inputs[2].attr("value"),
            inputs[3].attr("value"),
            inputs[4].attr("value"),
            inputs[5].attr("value"),
            inputs[6].attr("value"),
            inputs[7].attr("value")
        )
    }

    fun requestElectivesList(): List<Elective> {
        val formatter = DateTimeFormatterBuilder()
            .appendPattern("dd-MM-yyyy")
            .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
            .toFormatter()
            .withZone(ZoneId.systemDefault())

        return listOf(
            Elective(
                formatter.parse("27-07-2017", Instant::from),
                "Entrepreneurship",
                "eSports industry: marketing, economy and game design"
            ),
            Elective(
                formatter.parse("15-01-2019", Instant::from),
                "Technical",
                "Advanced С++: New Language Concepts, Features and Mechanisms"
            ),
            Elective(
                formatter.parse("11-08-2019", Instant::from),
                "Technical",
                "Reverse Engineering"
            ),
            Elective(
                formatter.parse("11-08-2019", Instant::from),
                "Technical",
                "Advanced Topics in Software Testing and Quality Management"
            ),
            Elective(
                formatter.parse("09-08-2018", Instant::from),
                "Technical",
                "Human-Computer Interaction Design for AI"
            ),
            Elective(
                formatter.parse("17-01-2020", Instant::from),
                "Technical",
                "Design Patterns"
            ),
            Elective(
                formatter.parse("17-01-2020", Instant::from),
                "Technical",
                "Consensus theory and concurrent programming on a shared memory"
            )
        )
    }

    fun requestCateringHistory(): CateringHistoryItemsList {
        return CateringHistoryItemsList(
            listOf(
                CateringHistoryItem(
                    LocalDate.parse("2018-08-20"),
                    LocalDate.parse("2018-12-15"),
                    listOf("lunch", "breakfast"),
                    5, BigDecimal.valueOf(3500)
                ),
                CateringHistoryItem(
                    LocalDate.parse("2019-01-15"),
                    LocalDate.parse("2019-05-31"),
                    listOf("lunch", "breakfast, dinner"),
                    5, BigDecimal.valueOf(4100)
                )
            )
        )
    }

    fun requestITServices(): ITServicesList {
        return ITServicesList(
            listOf(
                ITService(
                    "eDisk",
                    "Shared file storage (SMB, HTTPS, SFTP)",
                    "https://edisk.university.innopolis.ru"
                ),
                ITService(
                    "IT Support",
                    "Send request to Innopolis University IT Department",
                    "mailto:it@innopolis.ru"
                ),
                ITService("Mail", "Web access to email", "https://mail.innopolis.ru"),
                ITService(
                    "Moodle",
                    "Curriculum selection portal",
                    "https://moodle.university.innopolis.ru"
                )
            )
        )
    }

    private fun requestPage(path: String): Document {
        return Jsoup.connect(path)
            .method(Connection.Method.GET).cookies(this.cookies)
            .execute().parse()
    }
}