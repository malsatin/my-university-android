package com.example.myuniversityclient.data.repository.main

import android.util.Log
import com.example.myuniversityclient.data.models.AuthMessage
import com.example.myuniversityclient.data.models.ShortUserInfo
import com.example.myuniversityclient.data.repository.http.HttpClientService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class MainServiceHttp @Inject constructor(
    private val httpService: HttpClientService
) : MainService {

    override fun getShortUserInfo(onResult: (Result<ShortUserInfo?>) -> Unit) {
        Observable.create<ShortUserInfo> { emitter ->
            try {
                emitter.onNext(httpService.requestUserInfo())
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e("DBG", e.message!!)
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ShortUserInfo> {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}

                override fun onNext(userInfo: ShortUserInfo) {
                    onResult(Result.success(userInfo))
                }

                override fun onError(e: Throwable) {
                    onResult(Result.failure(e))
                }
            })
    }

    override fun auth(email: String, password: String, onResult: (Result<AuthMessage?>) -> Unit) {
        Observable.create<AuthMessage> { emitter ->
            try {
                emitter.onNext(httpService.auth(email, password))
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e("DBG", e.message!!)
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<AuthMessage> {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}

                override fun onNext(message: AuthMessage) {
                    onResult(Result.success(message))
                }

                override fun onError(e: Throwable) {
                    onResult(Result.failure(e))
                }
            })
    }

    override fun logout() {
        httpService.logout()
    }
}