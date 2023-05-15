package com.example.weatherapp.event


import com.example.weatherapp.data.models.Favourite

class FavouritesFetchedEvent(var favourites:  List<Favourite>?){
}