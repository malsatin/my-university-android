package com.example.myuniversityclient.data.repository.electives

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myuniversityclient.data.models.Elective
import javax.inject.Inject

class ElectivesRepository @Inject constructor(
    private val service: ElectivesService
) {
    fun getElectivesList(): LiveData<Result<List<Elective>>> {
        val liveData = MutableLiveData<Result<List<Elective>>>()

        service.getElectives {
            liveData.value = it
        }

        return liveData
    }
}