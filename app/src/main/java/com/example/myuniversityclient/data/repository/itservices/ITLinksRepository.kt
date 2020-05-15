package com.example.myuniversityclient.data.repository.itservices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myuniversityclient.data.models.ITServicesList
import javax.inject.Inject

/**
 * A repository used in [com.example.myuniversityclient.MainActivity].
 */
class ITLinksRepository @Inject constructor(
    private val itLinksService: ITLinksService
) {
    fun getITServicesList(): LiveData<Result<ITServicesList>> {
        val liveData = MutableLiveData<Result<ITServicesList>>()

        itLinksService.getITServices {
            liveData.value = it
        }

        return liveData
    }
}