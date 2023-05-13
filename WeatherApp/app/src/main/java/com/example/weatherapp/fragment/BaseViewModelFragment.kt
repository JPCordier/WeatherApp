package com.example.weatherapp.fragment

import android.os.Bundle
import android.view.View

import androidx.fragment.app.Fragment
import com.example.weatherapp.viewmodels.BaseViewModel
import com.example.weatherapp.viewmodels.MyResponse
import com.example.weatherapp.viewmodels.Status
import dagger.android.support.AndroidSupportInjection

abstract class BaseViewModelFragment<V : BaseViewModel> : Fragment() {

    protected lateinit var viewModel: V

    /**
     * In derived class, implement initialiseViewModel to create view model instance and set LiveData observers
     * For LiveData observers, use handleLiveDataResource as the handler in order to take advantage of helper methods
     * in this base class.
     */
    protected abstract fun initialiseViewModel()
    protected abstract fun setupViewModelObservers()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        initialiseViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelObservers()
    }

    protected fun <T> handleLiveDataResource(response: MyResponse<T>, handler: IResourceHandler<T>?) {
        setLoading(response.status == Status.LOADING)

         if (response.status == Status.SUCCESS) {
            handler?.onLiveDataLoaded(response.data)
        } else if (response.status == Status.FAILED) {
         //   onError(resource as ErrorResource<T>)
        }
    }

    protected fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    protected open fun showLoading() {
        //If derived fragment shows a progress bar, override this method and handle the visibility of the progress bar
    }

    protected open fun hideLoading() {
        //If derived fragment shows a progress bar, override this method and handle the visibility of the progress bar
    }

   // protected open fun <T> onError(error: ErrorResource<T>) {
    //    handleError(error)
  //  }


}