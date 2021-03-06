package com.example.myuniversityclient.data.repository.electives

import com.example.myuniversityclient.data.models.Elective

interface ElectivesService {
    fun getElectives(onResult: (Result<List<Elective>>) -> Unit)
}