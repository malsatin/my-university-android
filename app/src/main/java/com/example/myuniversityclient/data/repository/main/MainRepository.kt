package com.example.myuniversityclient.data.repository.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myuniversityclient.data.models.ShortUserInfo
import com.example.myuniversityclient.data.models.profile.*
import javax.inject.Inject

/**
 * A repository used in [com.example.myuniversityclient.MainActivity].
 */
class MainRepository @Inject constructor(
    private val service: MainService,
    private val profileService: ProfileService
) {
    /**
     * Last returned user info [LiveData].
     * Stored to later have the ability to send further updates to observers.
     */
    private var userInfo: MutableLiveData<Result<ShortUserInfo?>>? = null
    private var contacts : MutableLiveData<Result<Contacts?>>? = null
    private var educationHistory : MutableLiveData<Result<EducationHistory?>>? = null
    private var gradeBook : MutableLiveData<Result<GradeBook?>>? = null
    private var passports : MutableLiveData<Result<PassportData?>>? = null
    private var personalInfo : MutableLiveData<Result<PersonalInfo?>>? = null


    /**
     * Provides observable [LiveData] with requested user info to observers.
     * In case the requested info is unavailable due to expired or nonexistent
     * auth session, will return [Result.success] with a value of null.
     */
    fun getShortUserInfo(): LiveData<Result<ShortUserInfo?>> {
        val liveData = MutableLiveData<Result<ShortUserInfo?>>()
        userInfo = liveData

        service.getShortUserInfo {
            liveData.value = it
        }

        return liveData
    }

    fun getContacts(): LiveData<Result<Contacts?>> {
        val liveData = MutableLiveData<Result<Contacts?>>()
        contacts = liveData
        profileService.getContacts {
            liveData.value = it
        }

        return liveData
    }
    fun getEducationHistory(): LiveData<Result<EducationHistory?>> {
        val liveData = MutableLiveData<Result<EducationHistory?>>()
        educationHistory = liveData
        profileService.getEducationHistory {
            liveData.value = it
        }
        return liveData
    }
    fun getGradeBook(): LiveData<Result<GradeBook?>> {
        val liveData = MutableLiveData<Result<GradeBook?>>()
        gradeBook = liveData
        profileService.getGradeBook {
            liveData.value = it
        }
        return liveData
    }
    fun getPassports(): LiveData<Result<PassportData?>> {
        val liveData = MutableLiveData<Result<PassportData?>>()
        passports = liveData
        profileService.getPassportData {
            liveData.value = it
        }
        return liveData
    }
    fun getPersonalInfo(): LiveData<Result<PersonalInfo?>> {
        val liveData = MutableLiveData<Result<PersonalInfo?>>()
        personalInfo = liveData
        profileService.getPersonalInfo {
            liveData.value = it
        }
        return liveData
    }


    /**
     * Deletes information about current user.
     */
    fun logout() {
        userInfo?.value = null
        service.logout()
    }
}