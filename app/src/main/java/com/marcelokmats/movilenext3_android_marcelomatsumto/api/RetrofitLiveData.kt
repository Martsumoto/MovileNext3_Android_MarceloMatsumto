package com.marcelokmats.movilenext3_android_marcelomatsumto.api

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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