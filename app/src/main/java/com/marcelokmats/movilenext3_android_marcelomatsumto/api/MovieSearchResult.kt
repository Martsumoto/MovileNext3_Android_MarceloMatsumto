package com.marcelokmats.movilenext3_android_marcelomatsumto.api

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.marcelokmats.movilenext3_android_marcelomatsumto.db.Tables

data class MovieSearchResult(
    @SerializedName("Search")
    val movies: List<Movie>
)

@Entity(tableName = Tables.FAVORITE_MOVIES)
data class Movie(
    @PrimaryKey
    val imdbID: String,
    val Title: String?,
    val Year: String?,
    val Poster: String?) {
    @Ignore
    var isFavorite: Boolean = true
}

@Entity
data class MovieTicket(
    @PrimaryKey
    val imdbId: String,
    val Title: String?,
    val Year: String?,
    val Poster: String?,
    val amount: Int)