package com.example.weatherapp.fragment

interface IResourceHandler<T> {
    fun onLiveDataLoaded(data: T?)
}