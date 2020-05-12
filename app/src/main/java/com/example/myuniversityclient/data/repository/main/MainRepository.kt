package com.example.myuniversityclient.data.repository.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myuniversityclient.data.models.ITServicesList
import com.example.myuniversityclient.data.models.ShortUserInfo
import javax.inject.Inject

/**
 * A repository used in [com.example.myuniversityclient.MainActivity].
 */
class MainRepository @Inject constructor(
    private val service: MainService,
    private val itLinksService: ITLinksService
) {
    /**
     * Last returned user info [LiveData].
     * Stored to later have the ability to send further updates to observers.
     */
    private var userInfo: MutableLiveData<Result<ShortUserInfo?>>? = null
    private var itServices: MutableLiveData<Result<ITServicesList?>>? = null

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

    /**
     * Deletes information about current user.
     */
    fun logout() {
        userInfo?.value = null
        service.logout()
    }

    fun getITServicesList(): LiveData<Result<ITServicesList?>> {
        val liveData = MutableLiveData<Result<ITServicesList?>>()
        itServices = liveData

        itLinksService.getITServices {
            liveData.value = it
        }

        return liveData
    }
}