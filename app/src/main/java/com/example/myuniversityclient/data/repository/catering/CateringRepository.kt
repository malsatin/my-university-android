package com.example.myuniversityclient.data.repository.catering

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myuniversityclient.data.models.CateringHistoryItemsList
import javax.inject.Inject

/**
 * A repository used in [com.example.myuniversityclient.MainActivity].
 */
class CateringRepository @Inject constructor(
    private val cateringService: CateringService
) {
    fun getCateringHistoryList(): LiveData<Result<CateringHistoryItemsList>> {
        val liveData = MutableLiveData<Result<CateringHistoryItemsList>>()

        cateringService.getCateringHistory {
            liveData.value = it
        }

        return liveData
    }
}