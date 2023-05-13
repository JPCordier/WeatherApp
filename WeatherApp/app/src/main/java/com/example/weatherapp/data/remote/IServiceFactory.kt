package com.example.weatherapp.data.remote

import com.example.weatherapp.api.WeatherApiClient
import com.example.weatherapp.data.api.IWeatherApiClient

interface IServiceFactory {
    val weatherApiClient: IWeatherApiClient

}