package com.example.myuniversityclient.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myuniversityclient.data.models.CateringHistoryItemsList
import com.example.myuniversityclient.data.repository.catering.CateringRepository
import com.example.myuniversityclient.ui.models.CateringHistoryListModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CateringFragmentViewModel @Inject constructor(
    private val repository: CateringRepository
): AuthenticatedViewModel() {
    val cateringHistoryList: MutableLiveData<Result<CateringHistoryItemsList>> by lazy {
            loadCatering()
    }

    private fun loadCatering(): MutableLiveData<Result<CateringHistoryItemsList>> {
        return Transformations.map(repository.getCateringHistoryList()) { result ->
            result.map {
                if(it!=null) {
                    CateringHistoryListModel(
                        it.history
                    )
                }else{
                    null
                }
            }
        } as MutableLiveData<Result<CateringHistoryItemsList>>
    }

    override fun onAuthStateChanged() {
        cateringHistoryList.postValue(loadCatering().value)
    }
}
