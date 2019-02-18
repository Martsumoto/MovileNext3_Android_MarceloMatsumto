package com.marcelokmats.movilenext3_android_marcelomatsumto.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> LiveData<T>.observeOnce(observer: Observer<T>) {
    observeForever(object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

/**
 * https://gist.github.com/magneticflux-/044c9d7a3cea431aa0e4f4f4950a2898
 */
fun <A, B> zipLiveData()

class RetrofitLiveData<T>
    (private val call: Call<T>) : LiveData<T>(), Callback<T> {

    override fun onActive() {
        if (!call.isCanceled && !call.isExecuted) call.enqueue(this)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        t.stackTrace
        value = null
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        value = response.body()
    }
}