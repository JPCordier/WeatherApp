package com.example.weatherapp.fragment


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.FavouriteManager
import com.example.weatherapp.data.models.WeatherResult
import com.example.weatherapp.data.models.WeatherSummary
import com.example.weatherapp.data.models.WeatherType
import com.example.weatherapp.data.utils.StringUtils
import com.example.weatherapp.databinding.FragmentWeatherResultsBinding
import com.example.weatherapp.event.FavouriteSavedEvent
import com.example.weatherapp.event.LocationChangedEvent
import com.example.weatherapp.services.LocationService
import com.example.weatherapp.view.WeatherForecastResultsAdapter
import com.example.weatherapp.viewmodels.MyResponse
import com.example.weatherapp.viewmodels.WeatherResultViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.concurrent.Executors


open class WeatherResultsFragment : BaseViewModelFragment<WeatherResultViewModel>() {

    private var isFavourited: Boolean = false
    private val resultId: Int? = null
    protected open val shouldUpdateLocation: Boolean = true
    private var location: Location? = null
    lateinit var binding: FragmentWeatherResultsBinding
    var weatherForecastResultsAdapter: WeatherForecastResultsAdapter? = null
    private val favouriteManager: FavouriteManager = FavouriteManager.instance

    override fun initialiseViewModel() {
        viewModel = ViewModelProvider(this)[WeatherResultViewModel::class.java]

    }

    override fun setupViewModelObservers() {
        viewModel.weatherResultLiveData.observe(viewLifecycleOwner) { resource: MyResponse<WeatherResult> ->
            handleWeatherResult(
                resource
            )
        }
        viewModel.weatherForecastResultLiveData.observe(viewLifecycleOwner) { resource: MyResponse<List<WeatherSummary>> ->
            handleForecastResult(
                resource
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    private fun handleWeatherResult(resource: MyResponse<WeatherResult>) {
        setLoading(resource.isLoading())
        if (resource.isSuccess()) {
            weatherLoaded(resource.data)
        }
    }


    override fun showLoading() {
        binding.currentWeatherView.currentWeatherProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.currentWeatherView.currentWeatherProgressBar.visibility = View.GONE
    }

    private fun handleForecastResult(resource: MyResponse<List<WeatherSummary>>) {
        binding.weatherForecastView.searchResultsProgressBar.visibility =
            if (resource.isLoading()) View.VISIBLE else View.GONE
        if (resource.isSuccess()) {
            forecastLoaded(resource.data)
        }
    }

    private fun forecastLoaded(weatherForecastResults: List<WeatherSummary>?) {
        binding.weatherForecastView.recyclerSwipeContainer.isRefreshing = false
        if (weatherForecastResults != null) {
            if (weatherForecastResults.isNullOrEmpty()) {
                binding.weatherForecastView.noResults.visibility = View.VISIBLE
                binding.weatherForecastView.results.visibility = View.GONE
            } else {
                binding.weatherForecastView.noResults.visibility = View.GONE
                binding.weatherForecastView.results.visibility = View.VISIBLE
                weatherForecastResultsAdapter?.clear()
                weatherForecastResultsAdapter?.setItems(weatherForecastResults!!.toMutableList())
            }
        }
    }

    private fun weatherLoaded(loadedData: WeatherResult?) {
        if (loadedData != null) {
            when (loadedData.weatherType) {
                WeatherType.Cloudy -> {
                    binding.currentWeatherView.currentWeatherCard.setBackgroundColor(
                        requireContext().getColor(
                            R.color.cloudy
                        )
                    )
                    binding.currentWeatherView.currentWeatherCard.background =
                        requireContext().getDrawable(R.drawable.forest_cloudy)
                    binding.currentWeatherView.minMaxSection.setBackgroundColor(
                        requireContext().getColor(
                            R.color.cloudy
                        )
                    )
                    binding.weatherResultsSection.setBackgroundColor(
                        requireContext().getColor(
                            R.color.cloudy
                        )
                    )
                }

                WeatherType.Sunny -> {
                    binding.currentWeatherView.currentWeatherCard.setBackgroundColor(
                        requireContext().getColor(
                            R.color.sunny
                        )
                    )
                    binding.currentWeatherView.currentWeatherCard.background =
                        requireContext().getDrawable(R.drawable.forest_sunny)
                    binding.currentWeatherView.minMaxSection.setBackgroundColor(
                        requireContext().getColor(
                            R.color.sunny
                        )
                    )
                    binding.weatherResultsSection.setBackgroundColor(
                        requireContext().getColor(
                            R.color.sunny
                        )
                    )
                }

                WeatherType.Rainy -> {
                    binding.currentWeatherView.currentWeatherCard.setBackgroundColor(
                        requireContext().getColor(
                            R.color.rainy
                        )
                    )
                    binding.currentWeatherView.currentWeatherCard.background =
                        requireContext().getDrawable(R.drawable.forest_rainy)
                    binding.currentWeatherView.minMaxSection.setBackgroundColor(
                        requireContext().getColor(
                            R.color.rainy
                        )
                    )
                    binding.weatherResultsSection.setBackgroundColor(
                        requireContext().getColor(
                            R.color.rainy
                        )
                    )
                }

                null -> {}//do nothing
            }
            binding.currentWeatherView.currentTempDescription.text =
                StringUtils.appendDegreeToString(loadedData.description.toString())
            binding.currentWeatherView.minTemp.text =
                StringUtils.appendDegreeToString(loadedData.min.toString())
            binding.currentWeatherView.currentTemp.text =
                StringUtils.appendDegreeToString(loadedData.temperature.toString())
            binding.currentWeatherView.currentTempPrimary.text =
                StringUtils.appendDegreeToString(loadedData.temperature.toString())
            binding.currentWeatherView.maxTemp.text =
                StringUtils.appendDegreeToString(loadedData.max.toString())

        }
    }

    private fun favouriteClicked() {
        if(isFavourited) Toast.makeText(requireContext(),"Favourite Already Saved",Toast.LENGTH_LONG).show()
        else
        showDialog(requireContext())

    }

    open fun showDialog(context:Context) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.add_favourite_name_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dialogBtnCancel = dialog.findViewById<View>(R.id.btn_yes) as Button
        dialogBtnCancel.setOnClickListener { //
            var name = dialog.findViewById<View>(R.id.text_name) as EditText
            Executors.newSingleThreadExecutor().execute(Runnable {
                favouriteManager!!.toggleFavourite(resultId != null,name.text.toString(),location!!)
            })
            dialog.cancel()
        }
        val dialogBtnOk = dialog.findViewById<View>(R.id.btn_no) as Button
        dialogBtnOk.setOnClickListener { //
            dialog.cancel()
        }
        dialog.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_weather_results, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        requestLocationUpdates()
        initializeView()

    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun initializeView() {
        weatherForecastResultsAdapter = WeatherForecastResultsAdapter()
        binding.weatherForecastView.results.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.weatherForecastView.results.adapter = weatherForecastResultsAdapter

        binding.weatherForecastView.recyclerSwipeContainer.setOnRefreshListener {
            requestLocationUpdates()
        }
        binding.toggleFavourite.setOnClickListener{favouriteClicked()}
    }

    private fun updateWeatherResults() {
        viewModel.getWeatherAtMyLocation(location!!.latitude, location!!.longitude)
        viewModel.getForecastForMyLocation(location!!.latitude, location!!.longitude)
    }

    private fun requestLocationUpdates() {
        location = LocationService(requireContext() as Activity).location
    }

    @Subscribe
    fun onLocationChangedEvent(event: LocationChangedEvent?) {
        if (event?.location != null && shouldUpdateLocation) {
            location = event?.location
            updateWeatherResults()
            Toast.makeText(context,"Location Updated",Toast.LENGTH_LONG).show()
        }
    }

    @Subscribe
    fun OnFavouriteSavedEvent(event: FavouriteSavedEvent?) {
        binding?.toggleFavourite?.setImageDrawable(requireContext().getDrawable(R.drawable.ic_favourite_red))
        isFavourited = true
        Toast.makeText(context,"Favourite Saved",Toast.LENGTH_LONG).show()
    }
}