package com.example.weatherapp.viewmodels

import android.util.EventLog
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.models.Favourite
import com.example.weatherapp.data.repository.FavouritesRepository
import com.example.weatherapp.data.repository.IFavouritesRepository
import com.example.weatherapp.event.FavouritesFetchedEvent
import com.example.weatherapp.observers.LiveDataObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import org.greenrobot.eventbus.EventBus
import java.lang.Exception
import java.util.concurrent.Executors

import javax.inject.Inject

class FavouritesViewModel(private val favouritesRepository: IFavouritesRepository) :
    BaseViewModel() {
    @Inject
    constructor() : this(FavouritesRepository()) {
    }

    val favouriteLoadedLiveData = MutableLiveData<MyResponse<List<Favourite>>>()

    fun loadFavourites() {
        val observer = LiveDataObserver(favouriteLoadedLiveData)
        addObserver(observer)
        try {
            Executors.newSingleThreadExecutor().execute(Runnable {
                favouritesRepository.fetchFavouritesFromDB()
                    ?.subscribeOn(SchedulerProvider.instance.computation())
                    ?.observeOn(SchedulerProvider.instance.ui())
                    ?.subscribe(
                        { results: List<Favourite>? ->
                            EventBus.getDefault().post(FavouritesFetchedEvent(results))
                        },
                        { throwable: Throwable ->
                            EventLog.writeEvent(1, throwable.toString())
                        }  //On no result found
                    )

            })
        } catch (e: Exception) {
            EventLog.writeEvent(1, e.toString())
            favouriteLoadedLiveData.value = MyResponse(Status.FAILED, null)
        }


    }
}