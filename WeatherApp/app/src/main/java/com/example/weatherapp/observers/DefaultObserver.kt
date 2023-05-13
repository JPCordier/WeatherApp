package com.example.weatherapp.observers

import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableHelper
import io.reactivex.observers.DefaultObserver

open class DisposableObserver<T> : DefaultObserver<T>(){
     public fun dispose() {
        cancel()
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }

    override fun onComplete() {
    }
}