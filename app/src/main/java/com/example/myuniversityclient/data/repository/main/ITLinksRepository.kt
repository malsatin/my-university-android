package com.example.myuniversityclient.data.repository.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myuniversityclient.data.models.ITServicesList
import com.example.myuniversityclient.data.models.ShortUserInfo
import javax.inject.Inject

/**
 * A repository used in [com.example.myuniversityclient.MainActivity].
 */
class ITLinksRepository @Inject constructor(
    private val itLinksService: ITLinksService
) {
    private var itServices: MutableLiveData<Result<ITServicesList?>>? = null


    fun getITServicesList(): LiveData<Result<ITServicesList?>> {
        val liveData = MutableLiveData<Result<ITServicesList?>>()
        itServices = liveData

        itLinksService.getITServices {
            liveData.value = it
        }

        return liveData
    }
}