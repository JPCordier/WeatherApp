package com.example.weatherapp.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.WeatherApplication

@Database(version = 1, entities = [FavouriteEntity::class])


abstract class WeatherDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
    companion object {

        private const val DATABASE_NAME = "weather-app-database"

        @get:Synchronized
        val instance: WeatherDatabase by lazy {
            Room.databaseBuilder(
                WeatherApplication.appContext,
                WeatherDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}