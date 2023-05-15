package com.example.weatherapp.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.models.WeatherSummary
import com.example.weatherapp.data.models.WeatherType
import com.example.weatherapp.data.utils.MemUtils
import com.example.weatherapp.data.utils.StringUtils
import com.example.weatherapp.databinding.ViewWeatherForecastResultBinding
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

class WeatherForecastViewHolder(val binding: ViewWeatherForecastResultBinding) :  RecyclerView.ViewHolder(binding.root) {

    fun bindData(weatherSummary: WeatherSummary) {
        binding.forecastTemp.text = StringUtils.appendDegreeToString(weatherSummary.temperature.toString())
        if(weatherSummary.daysFromNow != null) {
            var date = LocalDate.now().plusDays(weatherSummary.daysFromNow!!.toLong())
            binding.day.text = date.dayOfWeek?.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        }
        when(weatherSummary.weatherType)
        {
            WeatherType.Cloudy -> {
                binding.weatherIcon.setImageResource(R.drawable.partlysunny3x)
            }
            WeatherType.Sunny ->{
                binding.weatherIcon.setImageResource(R.drawable.clear3x)
            }
            WeatherType.Rainy -> {
                binding.weatherIcon.setImageResource(R.drawable.rain3x)
            }
            null -> {}//do nothing
        }
    }

    fun onViewRecycled() {
        MemUtils.clearImages(binding.root, binding.weatherIcon)
    }
}