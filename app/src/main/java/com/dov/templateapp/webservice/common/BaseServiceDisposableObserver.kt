package com.dov.templateapp.webservice.common

import com.dov.templateapp.model.ApiErrorObject
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

abstract class BaseServiceDisposableObserver<T> : DisposableObserver<T>() {
    var errorResponse: ApiErrorObject? = null
    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
        if(e is RetrofitException){
            errorResponse = e.getErrorBodyAs(ApiErrorObject::class.java)
            Timber.d(javaClass.name + errorResponse?.message)
        }
    }

    override fun onNext(t: T) {

    }
}