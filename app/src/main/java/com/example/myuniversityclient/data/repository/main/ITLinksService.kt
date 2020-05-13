package com.example.myuniversityclient.data.repository.main

import com.example.myuniversityclient.data.models.ITServicesList

interface ITLinksService {
    fun getITServices(onResult: (Result<ITServicesList>) -> Unit)
}