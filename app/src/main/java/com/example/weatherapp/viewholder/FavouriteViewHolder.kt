package com.example.weatherapp.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.models.Favourite
import com.example.weatherapp.databinding.ViewFavouriteResultBinding

class FavouriteViewHolder (private val binding: ViewFavouriteResultBinding) :  RecyclerView.ViewHolder(binding.root) {

    fun bindData(favourite: Favourite) {
        binding.name.text = favourite.name
        binding.lat.text = favourite?.lat.toString()
        binding.longitude.text = favourite?.long.toString()
    }




}