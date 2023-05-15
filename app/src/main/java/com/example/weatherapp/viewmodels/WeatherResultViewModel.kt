package com.example.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.models.WeatherResult
import com.example.weatherapp.data.models.WeatherSummary
import com.example.weatherapp.data.repository.IWeatherRepository
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.observers.LiveDataObserver
import javax.inject.Inject



class WeatherResultViewModel (private val weatherRepository: IWeatherRepository) : BaseViewModel() {
     val weatherResultLiveData = MutableLiveData<MyResponse<WeatherResult>>()
    val weatherForecastResultLiveData = MutableLiveData<MyResponse<List<WeatherSummary>>>()


    @Inject
    constructor() : this(WeatherRepository()) {
    }


    fun getWeatherAtMyLocation(latitude : Double,longitude : Double ) {
        val observer = LiveDataObserver(weatherResultLiveData)
        addObserver(observer)
        weatherRepository.getWeatherByLatLong(latitude,longitude)
            .subscribeOn(schedulerProvider.computation())
            .observeOn(schedulerProvider.ui())
            .subscribe(observer)
    }

    fun getForecastForMyLocation(latitude : Double,longitude : Double ) {
        val observer = LiveDataObserver(weatherForecastResultLiveData)
        addObserver(observer)
        weatherRepository.getForecastByLatLong(latitude,longitude)
            .subscribeOn(schedulerProvider.computation())
            .observeOn(schedulerProvider.ui())
            .subscribe(observer)


    }
}