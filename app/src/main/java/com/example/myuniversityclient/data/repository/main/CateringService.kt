package com.example.myuniversityclient.data.repository.main

import com.example.myuniversityclient.data.models.CateringHistoryItemsList

interface CateringService {
    fun getCateringHistory(onResult: (Result<CateringHistoryItemsList>) -> Unit)
}