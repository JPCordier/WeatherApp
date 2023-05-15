package com.example.weatherapp.data.repository

import com.example.weatherapp.data.models.WeatherResult
import com.example.weatherapp.data.models.WeatherSummary
import com.example.weatherapp.data.remote.IServiceFactory
import com.example.weatherapp.data.remote.ServiceFactory
import io.reactivex.Observable


class WeatherRepository @JvmOverloads constructor(
    private val serviceFactory: IServiceFactory = ServiceFactory()) : IWeatherRepository
{
    override fun getWeatherByLatLong(latitude : Double , longitude : Double):  Observable<WeatherResult> {
        return serviceFactory.weatherApiClient.getWeatherByLatLong(latitude,longitude)
    }

    override fun getForecastByLatLong(
        latitude: Double,
        longitude: Double
    ): Observable<List<WeatherSummary>> {
        return serviceFactory.weatherApiClient.getForecastByLatLong(latitude,longitude)
    }
}
