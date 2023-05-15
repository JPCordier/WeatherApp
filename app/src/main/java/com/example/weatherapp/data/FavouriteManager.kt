package com.example.weatherapp.data

import android.location.Location
import android.util.EventLog
import com.example.weatherapp.data.repository.FavouritesRepository
import com.example.weatherapp.event.FavouriteSavedEvent
import com.example.weatherapp.event.LocationChangedEvent
import org.greenrobot.eventbus.EventBus

class FavouriteManager {
    fun toggleFavourite(isFavourited: Boolean,name : String,location:Location) {
        var favouritesRepository = FavouritesRepository()
        if (!isFavourited) {
            favouritesRepository.addFavourite(name,location).doAfterSuccess(
            {
                EventBus.getDefault().post(FavouriteSavedEvent())
            }).subscribe({ }
            )
            { throwable: Throwable -> EventLog.writeEvent(1,throwable.toString())
            }
        } else {

        }
    }

    companion object {
        @get:Synchronized
        val instance: FavouriteManager by lazy { FavouriteManager() }
    }
}