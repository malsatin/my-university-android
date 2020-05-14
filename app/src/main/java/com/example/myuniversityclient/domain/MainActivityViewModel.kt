package com.example.myuniversityclient.domain

import androidx.lifecycle.*
import com.example.myuniversityclient.data.repository.main.MainRepository
import com.example.myuniversityclient.ui.models.ShortUserInfoModel
import javax.inject.Inject

/**
 * A view model class that is used in [com.example.myuniversityclient.MainActivity].
 * Contains optional information about a current user.
 */
class MainActivityViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    fun getShortUserInfo(): LiveData<Result<ShortUserInfoModel?>> {
        // map to a new live data object
        return Transformations.map(repository.getShortUserInfo()) { result ->
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
