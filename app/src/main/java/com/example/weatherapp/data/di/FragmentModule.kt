package com.example.weatherapp.data.di

import com.example.weatherapp.fragment.FavouritesFragment
import com.example.weatherapp.fragment.WeatherResultsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeWeatherResultsFragment(): WeatherResultsFragment?
    @ContributesAndroidInjector
    abstract fun contributeFavouriteFragment(): FavouritesFragment?
}