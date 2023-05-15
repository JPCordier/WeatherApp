package com.example.weatherapp.data.models

class WeatherResult  : WeatherSummary() {
    var min: Int? = null
    var max: Int? = null
    var description: String? = null

    fun setMin(temperature: Double?): WeatherResult {
        this.min = temperature?.toInt()
        return this
    }

    fun setMax(temperature: Double?): WeatherResult {
        this.max = temperature?.toInt()
        return this
    }
    fun setDescription(description: String?): WeatherResult {
        this.description = description
        return this
    }

    override fun setTemperature (temperature: Double?): WeatherResult {
        return super.setTemperature(temperature) as WeatherResult
    }


    override fun setWeatherType(description: String?): WeatherResult {
        return super.setWeatherType(description) as WeatherResult
    }

    companion object {
        const val sunny = "Sunny"
        const val rainy= "Rainy"
        const val cloudy ="Cloudy"
    }

}