package com.example.myuniversityclient.data.repository.electives

import android.util.Log
import com.example.myuniversityclient.data.models.Elective
import com.example.myuniversityclient.data.repository.http.HttpClientService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ElectivesServiceHttp @Inject constructor(
    private val httpService: HttpClientService
) : ElectivesService {

    override fun getElectives(onResult: (Result<List<Elective>>) -> Unit) {
        Observable.create<List<Elective>> { emitter ->
            try {
                emitter.onNext(httpService.requestElectivesList())
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e("DBG", e.message!!)
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Elective>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}

                override fun onNext(userInfo: List<Elective>) {
                    onResult(Result.success(userInfo))
                }

                override fun onError(e: Throwable) {
                    onResult(Result.failure(e))
                }
            })
    }
}