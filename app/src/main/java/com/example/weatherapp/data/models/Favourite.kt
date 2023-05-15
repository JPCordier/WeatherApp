package com.example.weatherapp.data.models

class Favourite {
    var lat: Double? = null
    var long: Double? = null
    var name: String? = null

    fun setLat(latitude: Double?): Favourite {
        this.lat = latitude
        return this
    }

    fun setMax(longitude: Double?): Favourite {
        this.long = longitude
        return this
    }
    fun setName(description: String?): Favourite {
        this.name = description
        return this
    }

}