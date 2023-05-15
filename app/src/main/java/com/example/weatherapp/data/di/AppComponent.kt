package com.example.weatherapp.data.di

import com.example.weatherapp.WeatherApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [ViewModelModule::class,FragmentModule::class, AndroidSupportInjectionModule::class])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: WeatherApplication?): Builder?
        fun build(): AppComponent?
    }

    fun inject(appController: WeatherApplication?)
}