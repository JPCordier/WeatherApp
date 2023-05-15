package com.example.weatherapp.data.remote.mapper


import com.example.weatherapp.data.remote.model.WeatherSummaries
import com.example.weatherapp.data.remote.model.WeatherSummary
import io.reactivex.functions.Function

class WeatherSummaryMapper() : Function<WeatherSummaries, List<com.example.weatherapp.data.models.WeatherSummary>> {

    @Throws(Exception::class)
    fun apply(weatherSummary: WeatherSummary,daysFromNow: Int): com.example.weatherapp.data.models.WeatherSummary {
        return com.example.weatherapp.data.models.WeatherSummary()
            .setWeatherType(weatherSummary?.weather?.first()?.mainDescription)
            .setTemperature(weatherSummary.mainSummary?.temp)
            .setDayFromNow(daysFromNow)
    }

    override fun apply(summaries: WeatherSummaries): List<com.example.weatherapp.data.models.WeatherSummary> {
        val result: MutableList<com.example.weatherapp.data.models.WeatherSummary> = ArrayList()
        if (summaries.summaries.any()) {
            for ((index, value) in summaries.summaries.withIndex()) {
                result.add(apply(value,index+1))
            }


        }
        return result
    }
}