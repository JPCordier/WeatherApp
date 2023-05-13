package com.example.weatherapp.data.remote

import com.example.weatherapp.api.WeatherApiClient
import com.example.weatherapp.data.api.IWeatherApiClient
import com.example.weatherapp.data.remote.IServiceFactory


class ServiceFactory : IServiceFactory {
    override val weatherApiClient: IWeatherApiClient
        get() = WeatherApiClient.instance
}