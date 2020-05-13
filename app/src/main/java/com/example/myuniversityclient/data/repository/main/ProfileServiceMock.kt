package com.example.myuniversityclient.data.repository.main

import com.example.myuniversityclient.data.models.profile.*

class ProfileServiceMock: ProfileService {
    override fun getContacts(onResult: (Result<Contacts?>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getEducationHistory(onResult: (Result<EducationHistory?>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getGradeBook(onResult: (Result<GradeBook?>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getPassportData(onResult: (Result<PassportData?>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getPersonalInfo(onResult: (Result<PersonalInfo?>) -> Unit) {
        TODO("Not yet implemented")
    }
}