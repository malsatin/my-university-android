package com.example.myuniversityclient.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
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
) : ViewModel() {
    val electives: LiveData<Result<List<ElectiveItemModel>>> by lazy {
        Transformations.map(repository.getElectivesList()) { result ->
            result.map {
                it.map(::itemFromElective)
            }
        }
    }

    private val formatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy.MM.dd")
        .toFormatter()
        .withZone(ZoneId.systemDefault())

    private fun itemFromElective(elective: Elective): ElectiveItemModel {
        return ElectiveItemModel(
            elective.name,
            formatter.format(elective.subscriptionDate),
            elective.type
        )
    }
}