package com.example.weatherapp.data.models

open class WeatherSummary {
    var temperature: Int? = null
    var weatherType: WeatherType? = null
    var daysFromNow:Int? = null
    open fun setTemperature (temperature: Double?): WeatherSummary {
        this.temperature = temperature?.toInt()
        return this
    }


    open fun setWeatherType(description: String?): WeatherSummary {
        when(description){
            WeatherResult.rainy -> weatherType = WeatherType.Rainy
            WeatherResult.sunny ->  weatherType = WeatherType.Sunny
            WeatherResult.cloudy -> weatherType = WeatherType.Cloudy
            else ->
                //normal this case should be an invalid argument exception but will instead safely handle weathertypes outside the spec list ie cold,extreme etc.
                weatherType = WeatherType.Sunny
        }
        return this
    }

    open fun setDayFromNow(daysFromNow: Int):WeatherSummary{
        this.daysFromNow = daysFromNow
        return this
    }
}