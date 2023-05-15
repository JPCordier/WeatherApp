package com.example.weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.models.WeatherSummary
import com.example.weatherapp.databinding.ViewWeatherForecastResultBinding
import com.example.weatherapp.viewholder.WeatherForecastViewHolder
import java.util.ArrayList

class WeatherForecastResultsAdapter  : RecyclerView.Adapter<WeatherForecastViewHolder>() {
    private var results: MutableList<WeatherSummary> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewDealerTileBinding =
            ViewWeatherForecastResultBinding.inflate(layoutInflater, parent, false)
        return WeatherForecastViewHolder(viewDealerTileBinding)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder!!.bindData(results[position])
    }

    fun clear() {
        results.clear()
        notifyDataSetChanged()
    }

    fun setItems(results: MutableList<WeatherSummary>) {
        this.results = results
        notifyDataSetChanged()
    }
}