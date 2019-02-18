package com.marcelokmats.movilenext3_android_marcelomatsumto.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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
fun <A, B> zipLiveData(a: LiveData<A>, b: LiveData<B>): LiveData<Pair<A, B>> {
    return MediatorLiveData<Pair<A, B>>().apply {
        var lastA: A? = null
        var lastB: B? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            if (localLastA != null && localLastB != null)
                this.value = Pair(localLastA, localLastB)
        }

        addSource(a) {
            lastA = it
            update()
        }
        addSource(b) {
            lastB = it
            update()
        }
    }
}

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