package com.marcelokmats.movilenext3_android_marcelomatsumto.api

import android.os.Parcel
import android.os.Parcelable
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
    val Poster: String?) :Parcelable {
    @Ignore
    var isFavorite: Boolean = true

    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        isFavorite = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imdbID)
        parcel.writeString(Title)
        parcel.writeString(Year)
        parcel.writeString(Poster)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

data class MovieDetail(
    var Title: String?,
    var Year: String?,
    var Poster: String?,
    var ImdbID: String?,
    var Plot: String?,
    var Runtime: String?,
    var Director: String?,
    var Writer: String?,
    var Genre: String?)

@Entity(tableName = Tables.MOVIE_TICKETS)
data class MovieTicket(
    @PrimaryKey
    val imdbID: String,
    val Title: String?,
    val Year: String?,
    val Poster: String?,
    var amount: Int) {
    constructor(movie: Movie, amount: Int) : this(
        movie.imdbID,
        movie.Title,
        movie.Year,
        movie.Poster,
        amount) {
    }
}
