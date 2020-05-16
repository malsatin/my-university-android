package com.example.myuniversityclient.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myuniversityclient.data.repository.main.MainRepository
import com.example.myuniversityclient.ui.models.ShortUserInfoModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A view model class that is used in [com.example.myuniversityclient.MainActivity].
 * Contains optional information about a current user.
 */
@Singleton
class MainActivityViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    val shortUserInfo: LiveData<Result<ShortUserInfoModel?>> by lazy {
        Transformations.map(repository.getShortUserInfo()) { result ->
            result.map {
                if (it != null) {
                    ShortUserInfoModel(
                        it.avatarURL,
                        it.name,
                        it.email
                    )
                } else {
                    null
                }
            }
        }
    }
}
