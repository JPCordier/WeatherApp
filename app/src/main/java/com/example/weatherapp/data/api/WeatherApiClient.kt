package com.example.weatherapp.api

import com.example.weatherapp.data.api.IWeatherApiClient
import com.example.weatherapp.data.api.WeatherApiService
import com.example.weatherapp.data.models.WeatherResult
import com.example.weatherapp.data.remote.mapper.WeatherResultMapper
import com.example.weatherapp.data.remote.mapper.WeatherSummaryMapper
import com.example.weatherapp.data.models.WeatherSummary
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class WeatherApiClient : IApiServiceConfiguration, IWeatherApiClient {

    private val service: WeatherApiService
        get() {
           return retrofit!!.create(WeatherApiService::class.java)
        }
    override val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private var retrofit: Retrofit? = null
    private val apiKey : String = "6794d01473f75b686320a0ee9508ca52"


    private fun initialize() {

        val okBuilder = OkHttpClient.Builder()
        okBuilder.readTimeout(20, TimeUnit.SECONDS)
        okBuilder.connectTimeout(20, TimeUnit.SECONDS)

        val adapterBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
           .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())

        retrofit = adapterBuilder
            .client(okBuilder.build())
            .build()
    }

    init {
        initialize()
    }

    override fun getWeatherByLatLong(latitude : Double, longitude : Double): Observable<WeatherResult> {
      return service.getWeatherByLatLong(latitude,longitude,"metric",apiKey).doOnError { e -> }
           .map(WeatherResultMapper()).toObservable()

    }

    override fun getForecastByLatLong(latitude : Double, longitude : Double): Observable<List<WeatherSummary>> {
       return service.getWeatherForecastByLatLong(latitude,longitude,"metric",5,apiKey)
            .doOnError { e ->
          val a = e
        }.map(WeatherSummaryMapper()).toObservable()
    }


    companion object {
        @get:Synchronized
        val instance: WeatherApiClient by lazy {
            WeatherApiClient(
            )
        }
    }
}