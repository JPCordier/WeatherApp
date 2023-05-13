package com.example.weatherapp.data.models

class WeatherResult {

    var temperature: Double? = null
    var min: Double? = null
    var max: Double? = null
    var weatherType: WeatherType? = null
    var description: String? = null

    fun setTemperature (temperature: Double?): WeatherResult {
        this.temperature = temperature
        return this
    }

    fun setMin(temperature: Double?): WeatherResult {
        this.min = temperature
        return this
    }

    fun setMax(temperature: Double?): WeatherResult {
        this.max = temperature
        return this
    }

    fun setMainDescription(description: String?): WeatherResult {
        when(description){
            rainy-> weatherType = WeatherType.Rainy
            sunny->  weatherType = WeatherType.Sunny
            cloudy-> weatherType = WeatherType.Cloudy
            else ->
                //normal this case should be an invalid argument exception but will instead safely handle weathertypes outside the spec list ie cold,extreme etc.
                weatherType = WeatherType.Sunny
        }
        return this
    }

    fun setDescription(description: String?): WeatherResult {
        this.description = description
        return this
    }

    companion object {
        const val sunny = "Sunny"
        const val rainy= "Rainy"
        const val cloudy ="Cloudy"
    }

}