package com.example.weatherapp.data.api

import com.example.weatherapp.data.models.WeatherResult
import io.reactivex.Observable
import io.reactivex.Single

interface IWeatherApiClient{
    fun getWeatherByLatLong(latitude : Double, longitude : Double): Observable<WeatherResult>
}