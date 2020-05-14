package com.example.myuniversityclient.data.repository.main

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myuniversityclient.data.models.CateringHistoryItem
import com.example.myuniversityclient.data.models.CateringHistoryItemsList
import java.math.BigDecimal
import java.time.LocalDate


/**
 * Temporary backend substitution.
 */
class CateringServiceMock: CateringService {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getCateringHistory(onResult: (Result<CateringHistoryItemsList>) -> Unit) {
        val mockList = CateringHistoryItemsList(
            listOf(
                CateringHistoryItem (LocalDate.parse("2018-12-01"),
                    LocalDate.parse("2018-12-31"),
                    listOf("lunch", "breakfast"),
                    5, BigDecimal.valueOf(3500)),
                CateringHistoryItem (LocalDate.parse("2019-01-01"),
                    LocalDate.parse("2019-01-31"),
                    listOf("lunch", "breakfast, dinner"),
                    5, BigDecimal.valueOf(4100))));

        onResult(Result.success(mockList))
    }
}