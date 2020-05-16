package com.example.myuniversityclient.data.repository.profile

import android.util.Log
import com.example.myuniversityclient.data.models.profile.*
import com.example.myuniversityclient.data.repository.http.HttpClientService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ProfileServiceHttp @Inject constructor(
    private val httpService: HttpClientService
) : ProfileService {

    override fun getContacts(onResult: (Result<Contacts?>) -> Unit) {
        Observable.create<Contacts> { emitter ->
            try {
                emitter.onNext(httpService.requestProfileContacts())
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e("DBG", e.message!!)
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Contacts> {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}

                override fun onNext(userInfo: Contacts) {
                    onResult(Result.success(userInfo))
                }

                override fun onError(e: Throwable) {
                    onResult(Result.failure(e))
                }
            })
    }

    override fun getEducationHistory(onResult: (Result<EducationHistory?>) -> Unit) {
        Observable.create<EducationHistory> { emitter ->
            try {
                emitter.onNext(httpService.requestProfileEducationHistory())
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e("DBG", e.message!!)
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<EducationHistory> {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}

                override fun onNext(userInfo: EducationHistory) {
                    onResult(Result.success(userInfo))
                }

                override fun onError(e: Throwable) {
                    onResult(Result.failure(e))
                }
            })
    }

    override fun getGradeBook(onResult: (Result<GradeBook?>) -> Unit) {
        Observable.create<GradeBook> { emitter ->
            try {
                emitter.onNext(httpService.requestProfileGradeBook())
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e("DBG", e.message!!)
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<GradeBook> {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}

                override fun onNext(userInfo: GradeBook) {
                    onResult(Result.success(userInfo))
                }

                override fun onError(e: Throwable) {
                    onResult(Result.failure(e))
                }
            })
    }

    override fun getPassportData(onResult: (Result<PassportData?>) -> Unit) {
        Observable.create<PassportData> { emitter ->
            try {
                emitter.onNext(httpService.requestProfilePassport())
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e("DBG", e.message!!)
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<PassportData> {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}

                override fun onNext(userInfo: PassportData) {
                    onResult(Result.success(userInfo))
                }

                override fun onError(e: Throwable) {
                    onResult(Result.failure(e))
                }
            })
    }

    override fun getPersonalInfo(onResult: (Result<PersonalInfo?>) -> Unit) {
        Observable.create<PersonalInfo> { emitter ->
            try {
                emitter.onNext(httpService.requestProfilePersonalInfo())
                emitter.onComplete()
            } catch (e: Throwable) {
                Log.e("DBG", e.message!!)
                emitter.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<PersonalInfo> {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}

                override fun onNext(userInfo: PersonalInfo) {
                    onResult(Result.success(userInfo))
                }

                override fun onError(e: Throwable) {
                    onResult(Result.failure(e))
                }
            })
    }
}