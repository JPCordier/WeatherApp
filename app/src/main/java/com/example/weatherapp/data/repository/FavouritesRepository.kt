package com.example.weatherapp.data.repository

import android.location.Location
import com.example.weatherapp.data.models.Favourite

import com.example.weatherapp.data.services.DBService
import io.reactivex.Maybe
import io.reactivex.Observable

class FavouritesRepository @JvmOverloads constructor(private val dalService: DBService = DBService()) : IFavouritesRepository
{
    override fun fetchFavouritesFromDB(): Maybe<List<Favourite>>?  {


        return dalService.fetchFavourites()
    }
    override fun addFavourite(name : String,location: Location): Maybe<Long> {
        return dalService.addFavourite(name,location)
    }

}
