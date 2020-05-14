package com.example.myuniversityclient.data.repository.catering

import com.example.myuniversityclient.data.models.CateringHistoryItemsList

interface CateringService {
    fun getCateringHistory(onResult: (Result<CateringHistoryItemsList>) -> Unit)
}