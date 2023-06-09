package com.example.weatherapp.data.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.viewmodels.FavouritesViewModel
import com.example.weatherapp.viewmodels.WeatherResultViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherResultViewModel::class)
    protected abstract fun launcherWeatherResultViewModel(weatherResultViewModel: WeatherResultViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    protected abstract fun launcherFavouriteViewModel(favouritesViewModel: FavouritesViewModel?): ViewModel?

}