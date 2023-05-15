package com.example.weatherapp.observers

import android.util.EventLog
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.viewmodels.MyResponse
import io.reactivex.MaybeObserver
import java.lang.NullPointerException


open class LiveDataObserver<T>(protected var resource: MutableLiveData<MyResponse<T>>) : DisposableObserver<T>(),MaybeObserver<T> {

    @JvmField
    protected var result: T? = null

    override fun onStart() {
        super.onStart()
        resource.value = MyResponse.loading(result)
    }

    override fun onNext(t: T) {
        super.onNext(t)
        this.result = t
    }

    override fun onSuccess(t: T) {
        super.onNext(t)
        this.result = t
    }

    override fun onComplete() {
        resource.value = MyResponse.success(result)
    }

    override fun onError(e: Throwable) {
        if(e is NullPointerException) this.result = null
         resource.value = MyResponse.failed(e)
        EventLog.writeEvent(1,e.localizedMessage)
    }


}