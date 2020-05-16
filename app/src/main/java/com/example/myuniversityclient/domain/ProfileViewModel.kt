package com.example.myuniversityclient.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myuniversityclient.data.models.profile.* // ktlint-disable no-wildcard-imports
import com.example.myuniversityclient.data.repository.profile.ProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    fun getContacts(): LiveData<Result<Contacts?>> {
        return repository.getContacts()
    }

    fun getGradeBook(): LiveData<Result<GradeBook?>> {
        return repository.getGradeBook()
    }

    fun getEducationHistory(): LiveData<Result<EducationHistory?>> {
        return repository.getEducationHistory()
    }

    fun getPassports(): LiveData<Result<PassportData?>> {
        return repository.getPassports()
    }

    fun getPersonalInfo(): LiveData<Result<PersonalInfo?>> {
        return repository.getPersonalInfo()
    }
}