package com.example.weatherapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.fragment.BaseViewModelFragment
import com.example.weatherapp.viewmodels.WeatherResultViewModel

class WeatherResultsFragment : BaseViewModelFragment<WeatherResultViewModel>(){

    companion object {
        fun newInstance() = WeatherResultsFragment()
    }

    override fun initialiseViewModel() {
    }

    override fun setupViewModelObservers() {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_results, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[WeatherResultViewModel::class.java]

    }

}