package com.example.myuniversityclient.data.repository.itservices

import android.util.Log
import com.example.myuniversityclient.data.models.ITServicesList
import com.example.myuniversityclient.data.repository.http.HttpClientService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ITLinksServiceHttp @Inject constructor(
    private val httpService: HttpClientService
) : ITLinksService {

    override fun getITServices(onResult: (Result<ITServicesList>) -> Unit) {
        Observable.create<ITServicesList> { emitter ->
            try {
                emitter.onNext(httpService.requestITServices())
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e("DBG", e.message!!)
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ITServicesList> {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}

                override fun onNext(userInfo: ITServicesList) {
                    onResult(Result.success(userInfo))
                }

                override fun onError(e: Throwable) {
                    onResult(Result.failure(e))
                }
            })
    }
}