package com.example.weatherapp.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface FavouriteDao {
    @get:Query("SELECT * FROM FavouriteEntity")
    val all: Maybe<List<FavouriteEntity?>?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favouriteEntity: FavouriteEntity?): Maybe<Long?>?
}