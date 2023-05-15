package com.example.weatherapp.viewmodels

import androidx.compose.runtime.snapshots.SnapshotApplyResult


data class MyResponse<T>(
    var status: Status,
    var data: T? = null,
    var throwable: Throwable? = null
) {
    fun isSuccess(): Boolean {
        return  status == Status.SUCCESS
    }

    fun isLoading(): Boolean {
        return  status == Status.LOADING
    }
    fun isError(): Boolean {
        return  status == Status.FAILED
    }

    companion object {

        fun <T> loading(data: T?=null): MyResponse<T> {
            return MyResponse(
                status = Status.LOADING,
                data = data
            )
        }

        fun <T> success(data: T?=null): MyResponse<T> {
            return MyResponse(
                status = Status.SUCCESS,
                data = data
            )
        }

        fun <T> failed(throwable: Throwable): MyResponse<T> {
            return MyResponse(
                status = Status.FAILED,
                throwable = throwable
            )
        }
    }
}


enum class Status {
    LOADING,
    SUCCESS,
    FAILED,
}