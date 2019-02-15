package com.marcelokmats.movilenext3_android_marcelomatsumto.api

import com.google.gson.annotations.SerializedName

data class MovieSearchResult(
    @SerializedName("Search")
    val movies: List<Movie>
)

data class Movie(
    val title: String?,
    val year: Int?,
    val posterUrl: String?,
    val imdbId: String?)