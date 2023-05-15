package com.example.weatherapp.data.api

import com.example.weatherapp.data.remote.model.WeatherResult
import com.example.weatherapp.data.remote.model.WeatherSummaries
import com.example.weatherapp.data.remote.model.WeatherSummary
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather?")
    fun getWeatherByLatLong(@Query("lat") latitude: kotlin.Double,
                            @Query("lon") longitude: kotlin.Double,
                            @Query("units") unit : String,
                            @Query("appid")appId : kotlin.String): Single<WeatherResult>

    @GET("forecast?")
    fun getWeatherForecastByLatLong(@Query("lat") latitude: kotlin.Double,
                            @Query("lon") longitude: kotlin.Double,
                            @Query("units") unit : String,
                            @Query("cnt")amount : kotlin.Int,
                            @Query("appid")appId : kotlin.String): Single<WeatherSummaries>


}