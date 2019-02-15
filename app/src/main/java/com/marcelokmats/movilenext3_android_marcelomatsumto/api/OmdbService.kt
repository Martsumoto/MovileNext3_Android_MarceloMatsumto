package com.marcelokmats.movilenext3_android_marcelomatsumto.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbService {
    @GET("?type=movie")
    fun searchMovie(
        @Query("s") movieName: String
    ) : Call<MovieSearchResult>

}