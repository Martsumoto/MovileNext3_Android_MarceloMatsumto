package com.marcelokmats.movilenext3_android_marcelomatsumto.api

import com.google.gson.annotations.SerializedName

data class MovieSearchResult(
    @SerializedName("Search")
    val movies: List<Movie>
)

data class Movie(
    val Title: String?,
    val Year: String?,
    val Poster: String?,
    val imdbId: String?)