package com.example.weatherapp.data.remote.mapper



import com.example.weatherapp.data.remote.model.WeatherResult
import io.reactivex.functions.Function

class WeatherResultMapper() : Function<WeatherResult, com.example.weatherapp.data.models.WeatherResult> {

    @Throws(Exception::class)
    override fun apply(weatherResult: WeatherResult): com.example.weatherapp.data.models.WeatherResult {
        return com.example.weatherapp.data.models.WeatherResult()
            .setMax(weatherResult.max)
            .setMin(weatherResult.min)
            .setMainDescription(weatherResult.description)
            .setDescription(weatherResult.description)
            .setTemperature(weatherResult.temprature)
    }
}