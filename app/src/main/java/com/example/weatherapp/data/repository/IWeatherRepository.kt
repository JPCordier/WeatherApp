package com.example.weatherapp.data.repository

import com.example.weatherapp.data.models.WeatherResult
import com.example.weatherapp.data.models.WeatherSummary
import io.reactivex.Observable

interface IWeatherRepository {
     fun getWeatherByLatLong(latitude : Double , longitude : Double): Observable<WeatherResult>
     fun getForecastByLatLong(latitude : Double , longitude : Double): Observable<List<WeatherSummary>>
}