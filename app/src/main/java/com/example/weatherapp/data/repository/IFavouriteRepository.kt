package com.example.weatherapp.data.repository

import android.location.Location
import com.example.weatherapp.data.models.Favourite
import io.reactivex.Maybe
import io.reactivex.Observable

interface IFavouritesRepository {
    fun fetchFavouritesFromDB(): Maybe<List<Favourite>>?
    fun addFavourite(name : String,location: Location): Maybe<Long>
}