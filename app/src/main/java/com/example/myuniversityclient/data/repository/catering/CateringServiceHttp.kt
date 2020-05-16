package com.example.myuniversityclient.data.repository.catering

import android.util.Log
import com.example.myuniversityclient.data.models.CateringHistoryItemsList
import com.example.myuniversityclient.data.repository.http.HttpClientService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CateringServiceHttp @Inject constructor(
    private val httpService: HttpClientService
) : CateringService {

    override fun getCateringHistory(onResult: (Result<CateringHistoryItemsList>) -> Unit) {
        Observable.create<CateringHistoryItemsList> { emitter ->
            try {
                emitter.onNext(httpService.requestCateringHistory())
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e("DBG", e.message!!)
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<CateringHistoryItemsList> {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}

                override fun onNext(userInfo: CateringHistoryItemsList) {
                    onResult(Result.success(userInfo))
                }

                override fun onError(e: Throwable) {
                    onResult(Result.failure(e))
                }
            })
    }

}