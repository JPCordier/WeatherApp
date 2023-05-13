package com.example.weatherapp.data.repository

import com.example.weatherapp.data.models.WeatherResult
import io.reactivex.Observable

interface IWeatherRepository {
     fun getWeatherByLatLong(latitude : Double , longitude : Double): Observable<WeatherResult>
}