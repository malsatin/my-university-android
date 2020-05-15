package com.example.myuniversityclient.data.repository.profile

import com.example.myuniversityclient.data.models.profile.*
import java.util.*

class ProfileServiceMock :
    ProfileService {
    override fun getContacts(onResult: (Result<Contacts?>) -> Unit) {
        val mockContacts = Contacts(
            "Russia, Rep. Bashkortostan, Ufa",
            "Russia, Rep. Tatarstan, Innopolis",
            listOf("b.khabirov@innopolis.ru", "bulAtKhabiroff@gmail.ru"),
            listOf("@Mock"),
            listOf("89991543454", "88434321343")
        )
        onResult(Result.success(mockContacts))
    }

    override fun getEducationHistory(onResult: (Result<EducationHistory?>) -> Unit) {
        val mockEducationYear1 = EducationHistory.EducationYear(
            Date(),
            "Computer Science",
            "1",
            "BS16-01",
            "2016-2017",
            "IsStudent"

        )
        val mockEducationYear2 = EducationHistory.EducationYear(
            Date(),
            "CS",
            "2",
            "BS16-01",
            "2018-2019",
            "IsStudent"

        )
        val mockEducationYear3 = EducationHistory.EducationYear(
            Date(),
            "CS",
            "3",
            "BS16-01",
            "2019-2020",
            "IsStudent"

        )
        val mockEducationYear4 = EducationHistory.EducationYear(
            Date(),
            "CS",
            "4",
            "BS16-01",
            "2020-2021",
            "IsStudent"

        )
        val mockEducationHistory = EducationHistory(
            listOf(mockEducationYear1, mockEducationYear2, mockEducationYear3, mockEducationYear4)
        )
        onResult(Result.success(mockEducationHistory))

    }

    override fun getGradeBook(onResult: (Result<GradeBook?>) -> Unit) {
        val mockMark1 = GradeBook.Mark(
            "Android",
            "A. Simonenko",
            "B"
        )
        val mockMark2 = GradeBook.Mark(
            "Software quality and reliability",
            "A. Sadovukh",
            "A"
        )
        val mockMark3 = GradeBook.Mark(
            "Operating systems",
            "G.Succi",
            "A"
        )
        val mockGradeBook = GradeBook(
            listOf(mockMark1, mockMark2, mockMark3)
        )
        onResult(Result.success(mockGradeBook))

    }

    override fun getPassportData(onResult: (Result<PassportData?>) -> Unit) {
        val mockPassportInstance = Passport(
            "8081",
            "850890",
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
            "Bulat Khabirov",
            Date(),
            "Male",
            "Russia",
            "123463464124",
            "020623434564323",
            "EA6234321"
        )
        onResult(Result.success(mockPersonalInfo))

    }
}