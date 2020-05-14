package com.example.myuniversityclient.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myuniversityclient.data.models.ITServicesList
import com.example.myuniversityclient.data.repository.itservices.ITLinksRepository
import com.example.myuniversityclient.ui.models.ITServicesListModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ITServicesFragmentViewModel @Inject constructor(
    private val repository: ITLinksRepository
): ViewModel() {
    val itServicesList: LiveData<Result<ITServicesList>> by lazy {
        // map to a new live data object
        Transformations.map(repository.getITServicesList()) { result ->
            result.map {
                ITServicesListModel(
                    it!!.services
                )
            }
        }
    }
}
