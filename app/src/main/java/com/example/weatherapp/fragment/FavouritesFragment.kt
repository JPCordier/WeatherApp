package com.example.weatherapp.fragment

import android.app.Activity
import android.location.Location
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.viewmodels.FavouritesViewModel
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Favourite
import com.example.weatherapp.data.models.WeatherResult
import com.example.weatherapp.data.models.WeatherSummary
import com.example.weatherapp.data.models.WeatherType
import com.example.weatherapp.data.utils.StringUtils
import com.example.weatherapp.databinding.FragmentFavouritesBinding
import com.example.weatherapp.databinding.FragmentWeatherResultsBinding
import com.example.weatherapp.event.FavouriteSavedEvent
import com.example.weatherapp.event.FavouritesFetchedEvent
import com.example.weatherapp.event.LocationChangedEvent
import com.example.weatherapp.services.LocationService
import com.example.weatherapp.view.FavouritesAdapter
import com.example.weatherapp.view.WeatherForecastResultsAdapter
import com.example.weatherapp.viewmodels.MyResponse
import com.example.weatherapp.viewmodels.Status
import com.example.weatherapp.viewmodels.WeatherResultViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.concurrent.Executors

class FavouritesFragment : BaseViewModelFragment<FavouritesViewModel>() {


    lateinit var binding: FragmentFavouritesBinding
    var favourResultsAdapter: FavouritesAdapter? = null

    override fun initialiseViewModel() {
        viewModel = ViewModelProvider(this)[FavouritesViewModel::class.java]

    }

    override fun setupViewModelObservers() {
        viewModel.favouriteLoadedLiveData.observe(viewLifecycleOwner) { resource: MyResponse<List<Favourite>> ->
            handleFavouriteResult(
                resource
            )
        }
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    private fun handleFavouriteResult(resource:  MyResponse<List<Favourite>>) {
        binding.favouritesView.searchResultsProgressBar.visibility =
            if (resource.isLoading()) View.VISIBLE else View.GONE
        if (resource.isSuccess()) {
            favouriteLoaded(resource.data)
        }
        else if (resource.isError()) {
            binding.favouritesView.noResults.visibility = View.VISIBLE
            binding.favouritesView.results.visibility = View.GONE
         }
    }

    private fun favouriteLoaded(weatherForecastResults: List<Favourite>?) {
        binding.favouritesView.recyclerSwipeContainer.isRefreshing = false
        if (weatherForecastResults != null) {
            if (weatherForecastResults.isNullOrEmpty()) {
                binding.favouritesView.noResults.visibility = View.VISIBLE
                binding.favouritesView.results.visibility = View.GONE
            } else {
                binding.favouritesView.noResults.visibility = View.GONE
                binding.favouritesView.results.visibility = View.VISIBLE
                favourResultsAdapter?.clear()
                favourResultsAdapter?.setItems(weatherForecastResults!!.toMutableList())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        initializeView()

    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun initializeView() {
        favourResultsAdapter = FavouritesAdapter()
        binding.favouritesView.results.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.favouritesView.results.adapter = favourResultsAdapter
            viewModel.loadFavourites()

        binding.favouritesView.recyclerSwipeContainer.setOnRefreshListener {
             viewModel.loadFavourites()
        }
    }

    @Subscribe
    fun onFavouritesFetchedEvent(event: FavouritesFetchedEvent?) {
        viewModel.favouriteLoadedLiveData.value = MyResponse(Status.SUCCESS,event?.favourites)
    }


}