package com.example.weatherapp.data.services

import android.location.Location
import android.util.EventLog
import com.example.weatherapp.data.database.FavouriteEntity
import com.example.weatherapp.data.database.WeatherDatabase
import com.example.weatherapp.data.models.Favourite
import io.reactivex.Maybe

class DBService {
    fun fetchFavourites(): Maybe<List<Favourite>>? {
        return WeatherDatabase.instance.favouriteDao().all
            ?.map { favourites: List<FavouriteEntity?> ->
                if (favourites != null && favourites.isNotEmpty()) {
                    val result: MutableList<com.example.weatherapp.data.models.Favourite>? =
                        ArrayList()


                    for (favourite in favourites) {
                        result?.add(favourite!!.toModel())
                    }
                    return@map result
                }
                null
            }

    }

    fun addFavourite(name: String, location: Location): Maybe<Long> {
        var entity = FavouriteEntity()
        entity.lat = location.latitude
        entity.longitude = location.longitude
        entity.name = name

        return WeatherDatabase.instance.favouriteDao().insert(entity)!!.map { aLong: Long -> aLong
        }

    }
}

