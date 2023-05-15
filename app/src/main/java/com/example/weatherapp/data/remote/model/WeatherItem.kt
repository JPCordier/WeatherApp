package com.example.weatherapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherItem(


    @SerializedName("main")
    var mainDescription: String,
    @SerializedName("description")
    var description: String,
)