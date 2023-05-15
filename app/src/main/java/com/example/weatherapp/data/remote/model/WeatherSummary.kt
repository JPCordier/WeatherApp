package com.example.weatherapp.data.remote.model


import java.io.Serializable
import com.google.gson.annotations.SerializedName


data class WeatherSummaries(
    @SerializedName("list")
    val summaries: MutableList<WeatherSummary>) : Serializable {
}

data class WeatherSummary(

    @SerializedName("main")
    val mainSummary: MainSummary? = null,
    @field:SerializedName("weather")
    val weather: List<WeatherItem?>? = null,

    ) : Serializable {


}


data class MainSummary(

    @field:SerializedName("temp")
    val temp: Double? = null,

)

