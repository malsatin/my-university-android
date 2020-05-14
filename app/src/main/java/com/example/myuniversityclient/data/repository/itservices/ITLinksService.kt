package com.example.myuniversityclient.data.repository.itservices

import com.example.myuniversityclient.data.models.ITServicesList

interface ITLinksService {
    fun getITServices(onResult: (Result<ITServicesList>) -> Unit)
}