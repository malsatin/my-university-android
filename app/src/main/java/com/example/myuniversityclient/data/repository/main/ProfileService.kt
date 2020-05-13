package com.example.myuniversityclient.data.repository.main

import com.example.myuniversityclient.data.models.ShortUserInfo
import com.example.myuniversityclient.data.models.profile.*

/**
 * A DAO for retrieving profile.
 */
interface ProfileService {
    /**
     * Makes a request for retrieving contacts.
     * @param onResult a callback with a result of the request.
     */
    fun getContacts(onResult: (Result<Contacts?>) -> Unit)

    /**
     * Makes a request for retrieving year-by-year education history.
     * @param onResult a callback with a result of the request.
     */
    fun getEducationHistory(onResult: (Result<EducationHistory?>) -> Unit)

    /**
     * Makes a request for retrieving gradebook.
     * @param onResult a callback with a result of the request.
     */
    fun getGradeBook(onResult: (Result<GradeBook?>) -> Unit)

    /**
     * Makes a request for retrieving passports.
     * @param onResult a callback with a result of the request.
     */
    fun getPassportData(onResult: (Result<PassportData?>) -> Unit)

    /**
     * Makes a request for retrieving personal info.
     * @param onResult a callback with a result of the request.
     */
    fun getPersonalInfo(onResult: (Result<PersonalInfo?>) -> Unit)

}