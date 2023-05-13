package com.example.weatherapp.data.remote.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class WeatherResult (
    @SerializedName("main.temp")
    var temprature: kotlin.Double,
    @SerializedName("main.temp_min")
    var min: kotlin.Double,
    @SerializedName("main.temp_max")
    var max: kotlin.Double,
    @SerializedName("weather.main")
    var mainDescription: String,
    @SerializedName("weather.description")
    var description: String,
) : Serializable {


}