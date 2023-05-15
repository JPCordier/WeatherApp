package com.example.weatherapp

import android.app.Application
import android.content.Context
import com.example.weatherapp.data.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class WeatherApplication :Application(),HasAndroidInjector{


    @JvmField
    @Inject
    var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>? = null

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        DaggerAppComponent.builder()
            .application(this)
            ?.build()
            ?.inject(this)

     //   ProcessLifecycleOwner.get().lifecycle.addObserver(SessionManager.instance)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector!!
    }

    companion object {
        @JvmStatic
        lateinit var appContext: Context
            private set
    }
}