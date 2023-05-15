package com.example.weatherapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.data.models.Favourite

@Entity
class FavouriteEntity {
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var id = 0
    val isNew: Boolean
        get() = id == 0

    @JvmField
    var lat: Double? = null

    @JvmField
    var longitude: Double? = null

    @JvmField
    var name: String? = null

    fun toModel(): Favourite {
        val favourite = Favourite()

        favourite.lat = lat
        favourite.long = longitude
        favourite.name = name
        return  favourite
    }
}