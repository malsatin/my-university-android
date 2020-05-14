package com.example.myuniversityclient.data.repository.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myuniversityclient.data.models.CateringHistoryItemsList
import com.example.myuniversityclient.data.models.ShortUserInfo
import javax.inject.Inject

/**
 * A repository used in [com.example.myuniversityclient.MainActivity].
 */
class CateringRepository @Inject constructor(
    private val cateringService: CateringService
) {
    private var cateringHistoryItems: MutableLiveData<Result<CateringHistoryItemsList?>>? = null

    fun getCateringHistoryList(): LiveData<Result<CateringHistoryItemsList?>> {
        val liveData = MutableLiveData<Result<CateringHistoryItemsList?>>()
        cateringHistoryItems = liveData

        cateringService.getCateringHistory {
            liveData.value = it
        }

        return liveData
    }
}