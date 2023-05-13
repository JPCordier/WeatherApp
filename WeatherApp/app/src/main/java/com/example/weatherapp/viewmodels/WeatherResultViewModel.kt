package com.example.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.engine.Resource
import com.example.weatherapp.data.models.WeatherResult
import com.example.weatherapp.data.repository.IWeatherRepository
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.observers.LiveDataObserver
import javax.inject.Inject



class WeatherResultViewModel (private val weatherRepository: IWeatherRepository) : BaseViewModel() {
    private val weatherResultLiveData = MutableLiveData<MyResponse<WeatherResult>>()
    var locationchanged: Boolean = false

    @Inject
    constructor() : this(WeatherRepository()) {
    }

    fun clearWeatherResultLiveData() {
      weatherResultLiveData.value = null
    }

    fun getWeatherAtMyLocation(latitude : Double,longitude : Double ) {
        val observer = LiveDataObserver(weatherResultLiveData)
        addObserver(observer)
        weatherRepository.getWeatherByLatLong(latitude,longitude)
            .subscribeOn(schedulerProvider.computation())
            .observeOn(schedulerProvider.ui())
            .subscribe(observer)
    }
}