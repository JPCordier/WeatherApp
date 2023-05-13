package com.example.weatherapp.services

import android.location.Location

interface ILocationService {
    val location: Location?
    fun start()
    fun stop()
}