package com.example.weatherapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.PropertyChangeRegistry
import com.example.weatherapp.observers.DisposableObserver
import com.example.weatherapp.observers.WeakObserverCollection
import com.example.weatherapp.viewmodels.SchedulerProvider.Companion.instance
import io.reactivex.Observer

open class BaseViewModel : ViewModel, Observable {

    protected val schedulerProvider: ISchedulerProvider
    private val callbacks = PropertyChangeRegistry()
    private val currentObservers: WeakObserverCollection<DisposableObserver<*>> = WeakObserverCollection()

    constructor() {
        schedulerProvider = instance
    }

    constructor(schedulerProvider: ISchedulerProvider) {
        this.schedulerProvider = schedulerProvider
    }

    protected fun addObserver(observer: DisposableObserver<*>) {
        currentObservers.add(observer)
    }

    override fun onCleared() {
        disposeObserver()
    }

    private fun disposeObserver() {
        currentObservers.disposeItems()
    }

    override fun addOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    protected fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    protected fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}