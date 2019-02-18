package com.marcelokmats.movilenext3_android_marcelomatsumto.api

import com.marcelokmats.movilenext3_android_marcelomatsumto.util.RetrofitLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRetriever {
    private val service: OmdbService

    companion object {
        const val BASE_URL = "http://www.omdbapi.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(OmdbService::class.java)
    }

    fun getMoviesSearchResult(query: String) : RetrofitLiveData<MovieSearchResult> =
        RetrofitLiveData(service.searchMovie(query))

    fun getMovieDetail(imdbID: String) : RetrofitLiveData<MovieDetail> =
        RetrofitLiveData(service.getMovieDetail(imdbID))
}