package com.example.weatherapp.data.utils

object StringUtils {
    @kotlin.jvm.JvmStatic
    fun appendDegreeToString(value : String) : String
    {
        return value + "\u00B0"
    }
}