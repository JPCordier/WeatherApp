package com.example.weatherapp.data.api

import com.example.weatherapp.data.models.WeatherResult
import com.example.weatherapp.data.models.WeatherSummary
import io.reactivex.Observable
import io.reactivex.Single

interface IWeatherApiClient{
    fun getWeatherByLatLong(latitude : Double, longitude : Double): Observable<WeatherResult>
    fun getForecastByLatLong(latitude : Double, longitude : Double): Observable<List<WeatherSummary>>
}