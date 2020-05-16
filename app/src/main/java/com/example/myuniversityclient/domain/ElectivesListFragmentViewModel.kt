package com.example.myuniversityclient.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.myuniversityclient.data.models.Elective
import com.example.myuniversityclient.data.repository.electives.ElectivesRepository
import com.example.myuniversityclient.ui.models.ElectiveItemModel
import java.time.ZoneId
import java.time.format.DateTimeFormatterBuilder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ElectivesListFragmentViewModel @Inject constructor(
    private val repository: ElectivesRepository
): AuthenticatedViewModel() {
    val electives: MutableLiveData<Result<List<ElectiveItemModel>>> by lazy {
        loadElectives()
    }

    private val formatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy.MM.dd")
        .toFormatter()
        .withZone(ZoneId.systemDefault())

    fun loadElectives(): MutableLiveData<Result<List<ElectiveItemModel>>>{
        return Transformations.map(repository.getElectivesList()) { result ->
            result.map {
                if(it!=null) {
                    it.map(::itemFromElective)
                }else{
                    null
                }
            }
        } as MutableLiveData<Result<List<ElectiveItemModel>>>
    }

    private fun itemFromElective(elective: Elective): ElectiveItemModel {
        return ElectiveItemModel(
            elective.name,
            formatter.format(elective.subscriptionDate),
            elective.type
        )
    }

    override fun onAuthStateChanged() {
        electives.postValue(loadElectives().value)
    }
}