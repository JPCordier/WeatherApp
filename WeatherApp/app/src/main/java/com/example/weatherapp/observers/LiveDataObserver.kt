package com.example.weatherapp.observers

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.viewmodels.MyResponse


open class LiveDataObserver<T>(protected var resource: MutableLiveData<MyResponse<T>>) : DisposableObserver<T>() {

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

    override fun onComplete() {
        resource.value = MyResponse.success(result)
    }


}