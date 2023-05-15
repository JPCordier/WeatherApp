package com.example.weatherapp


import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val navController = findNavController(R.id.main_home_fragment)

        val topLevelDestinations: MutableSet<Int> = HashSet()
        topLevelDestinations.add(R.id.WeatherResultsFragment)
        topLevelDestinations.add(R.id.FavouritesFragment)
        var appBarConfiguration = AppBarConfiguration.Builder( //Put all menu item destinations here.
            topLevelDestinations
        ).setOpenableLayout(binding.drawerLayout).build()
        NavigationUI.setupWithNavController(binding.contentMain.toolbar, navController, appBarConfiguration)
        findViewById<NavigationView>(R.id.nav_menu).setupWithNavController(navController)

    }






}

