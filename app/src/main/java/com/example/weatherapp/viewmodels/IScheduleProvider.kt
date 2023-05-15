package com.example.weatherapp.viewmodels

import io.reactivex.Scheduler

interface ISchedulerProvider {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
}