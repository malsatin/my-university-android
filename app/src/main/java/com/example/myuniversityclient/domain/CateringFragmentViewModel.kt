package com.example.myuniversityclient.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myuniversityclient.data.models.CateringHistoryItemsList
import com.example.myuniversityclient.data.repository.catering.CateringRepository
import com.example.myuniversityclient.ui.models.CateringHistoryListModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CateringFragmentViewModel @Inject constructor(
    private val repository: CateringRepository
): ViewModel() {
    val cateringHistoryList: LiveData<Result<CateringHistoryItemsList>> by lazy {
        Transformations.map(repository.getCateringHistoryList()) { result ->
            result.map {
                CateringHistoryListModel(
                    it.history
                )
            }
        }
    }
}
