package com.example.myuniversityclient.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myuniversityclient.data.models.ITServicesList
import com.example.myuniversityclient.data.repository.itservices.ITLinksRepository
import com.example.myuniversityclient.ui.models.ITServicesListModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ITServicesFragmentViewModel @Inject constructor(
    private val repository: ITLinksRepository
): AuthenticatedViewModel() {
    val itServicesList: MutableLiveData<Result<ITServicesList>> by lazy {
        // map to a new live data object
        loadItServices()
    }

    fun loadItServices(): MutableLiveData<Result<ITServicesList>>{
        return Transformations.map(repository.getITServicesList()) { result ->
            result.map {
                if(it!=null) {
                    ITServicesListModel(
                        it.services
                    )
                }else{
                    null
                }
            }
        } as MutableLiveData<Result<ITServicesList>>
    }
    override fun onAuthStateChanged() {
        itServicesList.postValue(loadItServices().value)
    }
}
