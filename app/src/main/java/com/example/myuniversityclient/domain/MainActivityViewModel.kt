package com.example.myuniversityclient.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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
): AuthenticatedViewModel() {
    val shortUserInfo: MutableLiveData<Result<ShortUserInfoModel?>> by lazy {
        loadShortUserInfo()
    }

    fun loadShortUserInfo(): MutableLiveData<Result<ShortUserInfoModel?>>{
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
        } as MutableLiveData<Result<ShortUserInfoModel?>>
    }
    override fun onAuthStateChanged() {
        shortUserInfo.postValue(loadShortUserInfo().value)
    }
}
