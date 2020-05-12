package com.example.myuniversityclient.data.repository.main

import com.example.myuniversityclient.data.models.ITService
import com.example.myuniversityclient.data.models.ITServicesList


/**
 * Temporary backend substitution.
 */
class ITLinksServiceMock: ITLinksService {
    override fun getITServices(onResult: (Result<ITServicesList?>) -> Unit) {
        val mockList = ITServicesList(
            listOf(
                ITService ("eDisk", "Shared file storage (SMB, HTTPS, SFTP)", "edisk.university.innopolis.ru"),
                ITService ("IT Support", "Send request to Innopolis University IT Department", "mailto:it@innopolis.ru"),
                ITService ("Mail", "Web access to email", "mail.innopolis.ru"),
                ITService ("Moodle", "Curriculum selection portal", "moodle.university.innopolis.ru")
                )
        );

        onResult(Result.success(mockList))
    }
}