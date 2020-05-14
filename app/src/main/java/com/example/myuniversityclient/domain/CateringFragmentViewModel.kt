package com.example.myuniversityclient.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myuniversityclient.data.models.CateringHistoryItemsList
import com.example.myuniversityclient.data.repository.main.CateringRepository
import com.example.myuniversityclient.ui.models.CateringHistoryListModel
import javax.inject.Inject

class CateringFragmentViewModel @Inject constructor(
    private val repository: CateringRepository
): ViewModel() {

    fun getCateringHistoryList(): LiveData<Result<CateringHistoryItemsList?>> {
        // map to a new live data object
        return Transformations.map(repository.getCateringHistoryList()) { result ->
            result.map {
                if (it != null) {
                    CateringHistoryListModel(
                        it.history
                    )
                } else {
                    null
                }
            }
        }
    }

}
