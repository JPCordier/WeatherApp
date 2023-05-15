package com.example.weatherapp.data.remote.mapper



import com.example.weatherapp.data.remote.model.WeatherResult
import io.reactivex.functions.Function

class WeatherResultMapper() : Function<WeatherResult, com.example.weatherapp.data.models.WeatherResult> {

    @Throws(Exception::class)
    override fun apply(weatherResult: WeatherResult): com.example.weatherapp.data.models.WeatherResult {
        var singleWeatherItem = weatherResult.weather?.first()
        return com.example.weatherapp.data.models.WeatherResult()
            .setMax(weatherResult.main?.max)
            .setMin(weatherResult.main?.min)
            .setWeatherType(singleWeatherItem?.mainDescription)
            .setDescription(singleWeatherItem?.description)
            .setTemperature(weatherResult.main?.temp)
    }
}