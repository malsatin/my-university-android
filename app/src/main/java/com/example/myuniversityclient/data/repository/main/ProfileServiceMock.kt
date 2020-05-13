package com.example.myuniversityclient.data.repository.main

import android.provider.ContactsContract
import com.example.myuniversityclient.data.models.profile.*
import java.util.*

class ProfileServiceMock: ProfileService {
    override fun getContacts(onResult: (Result<Contacts?>) -> Unit) {
        val mockContacts = Contacts(
            "Russia, Rep. Bashkortostan, Ufa",
            "Russia, Rep. Tatarstan, Innopolis",
            listOf("mock@innopolis.ru"),
            listOf("@Mock"),
            listOf("89999999999")
        )
        onResult(Result.success(mockContacts))
    }

    override fun getEducationHistory(onResult: (Result<EducationHistory?>) -> Unit) {
        val mockEducationYear = EducationHistory.EducationYear(
            Date(),
            "CS",
            "1",
            "BS20-01",
            "2020-2021",
            "IsStudent"

        )
        val mockEducationHistory = EducationHistory(
            listOf(mockEducationYear)
        )
        onResult(Result.success(mockEducationHistory))

    }

    override fun getGradeBook(onResult: (Result<GradeBook?>) -> Unit) {
        val mockMark = GradeBook.Mark(
            "Android",
            "A. Simonenko",
            "A"
        )
        val mockGradeBook = GradeBook(
            listOf(mockMark)
        )
        onResult(Result.success(mockGradeBook))

    }

    override fun getPassportData(onResult: (Result<PassportData?>) -> Unit) {
        val mockPassportInstance = Passport(
            "8080",
            "890890",
            Date(),
            "020-033"
        )
        val mockPassports = PassportData(
            listOf(mockPassportInstance)
        )
        onResult(Result.success(mockPassports))

    }

    override fun getPersonalInfo(onResult: (Result<PersonalInfo?>) -> Unit) {
        val mockPersonalInfo = PersonalInfo(
                "Ivan Ivanovich Ivanov",
            Date(),
            "Male",
            "USA",
            "123463464124",
            "",
            "EA1234321"
                )
        onResult(Result.success(mockPersonalInfo))

    }
}