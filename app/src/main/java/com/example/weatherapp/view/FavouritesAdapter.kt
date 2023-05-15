package com.example.weatherapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.models.Favourite
import com.example.weatherapp.databinding.ViewFavouriteResultBinding
import com.example.weatherapp.viewholder.FavouriteViewHolder

class FavouritesAdapter : RecyclerView.Adapter<FavouriteViewHolder>() ,View.OnClickListener{
    private var results: MutableList<Favourite> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding =
            ViewFavouriteResultBinding.inflate(layoutInflater, parent, false)
        viewBinding.root.setOnClickListener(this)
        return FavouriteViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder!!.bindData(results[position])

    }

    fun clear() {
        results.clear()
        notifyDataSetChanged()
    }

    fun setItems(results: MutableList<Favourite>) {
        this.results = results
        notifyDataSetChanged()
    }

    override fun onClick(v: View?) {

    }
}