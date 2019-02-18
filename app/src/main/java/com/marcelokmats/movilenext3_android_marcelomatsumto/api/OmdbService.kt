package com.marcelokmats.movilenext3_android_marcelomatsumto.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbService {
    @GET("?apikey=21cf54f8")
    fun searchMovie(
        @Query("s") movieName: String
    ) : Call<MovieSearchResult>

    @GET("?apikey=21cf54f8&type=movie")
    fun getMovieDetail(
        @Query("i") imdbID: String
    ) : Call<MovieDetail>
}