package com.example.myuniversityclient.data.repository.catering

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myuniversityclient.data.models.CateringHistoryItem
import com.example.myuniversityclient.data.models.CateringHistoryItemsList
import java.time.LocalDate

/**
 * Temporary backend substitution.
 */
class CateringServiceMock : CateringService {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getCateringHistory(onResult: (Result<CateringHistoryItemsList>) -> Unit) {
        val mockList = CateringHistoryItemsList(
            listOf(
                CateringHistoryItem(
                    LocalDate.parse("2018-08-20"),
                    LocalDate.parse("2018-12-15"),
                    listOf("lunch", "breakfast"),
                    5, "3500 rub"
                ),
                CateringHistoryItem(
                    LocalDate.parse("2019-01-15"),
                    LocalDate.parse("2019-05-31"),
                    listOf("lunch", "breakfast, dinner"),
                    5, "4100 rub"
                )
            )
        )

        onResult(Result.success(mockList))
    }
}