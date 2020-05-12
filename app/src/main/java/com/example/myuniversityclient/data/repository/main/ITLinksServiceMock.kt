package com.example.myuniversityclient.data.repository.main

import com.example.myuniversityclient.data.models.ITService
import com.example.myuniversityclient.data.models.ITServicesList


/**
 * Temporary backend substitution.
 */
class ITLinksServiceMock: ITLinksService {
    override fun getITServices(onResult: (Result<ITServicesList?>) -> Unit) {
        val mockList = ITServicesList(
            listOf(ITService ("test", "test", "test"))
        );

        onResult(Result.success(mockList))
    }
}