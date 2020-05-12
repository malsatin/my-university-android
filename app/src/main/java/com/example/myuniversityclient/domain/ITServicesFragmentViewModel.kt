package com.example.myuniversityclient.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myuniversityclient.data.models.ITServicesList
import com.example.myuniversityclient.data.repository.main.MainRepository
import com.example.myuniversityclient.ui.models.ITServicesListModel
import com.example.myuniversityclient.ui.models.ShortUserInfoModel
import javax.inject.Inject

class ITServicesFragmentViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    fun getITServicesList(): LiveData<Result<ITServicesList?>> {
        // map to a new live data object
        return Transformations.map(repository.getITServicesList()) { result ->
            result.map {
                if (it != null) {
                    ITServicesListModel(
                        it.services
                    )
                } else {
                    null
                }
            }
        }
    }

}
