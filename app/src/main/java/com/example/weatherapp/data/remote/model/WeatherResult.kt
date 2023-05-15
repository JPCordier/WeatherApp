package com.example.weatherapp.data.remote.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName

data class WeatherResult(

    @SerializedName("main")
    val main: Main? = null,
    @field:SerializedName("weather")
    val weather: List<WeatherItem?>? = null,

    ) : Serializable {


}

data class Main(

    @field:SerializedName("temp")
    val temp: Double? = null,
    @SerializedName("temp_min")
    var min: kotlin.Double,

    @SerializedName("temp_max")
    var max: kotlin.Double,
)

