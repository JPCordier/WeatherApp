package com.example.weatherapp.data.api

import com.example.weatherapp.data.remote.model.WeatherResult
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApiService {

    @POST("weather")
    fun getWeatherByLatLong(@Query("lat") latitude: kotlin.Double,@Query("longitude") longitude: kotlin.Double, @Query("units") unit : String,  @Query("appId")appId : kotlin.String): Single<WeatherResult>


}