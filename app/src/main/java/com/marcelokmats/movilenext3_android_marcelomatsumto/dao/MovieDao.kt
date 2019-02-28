package com.marcelokmats.movilenext3_android_marcelomatsumto.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieTicket
import com.marcelokmats.movilenext3_android_marcelomatsumto.db.Tables

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorite(movie: Movie)

    @Query("SELECT * FROM ${Tables.FAVORITE_MOVIES}")
    fun getFavorites(): LiveData<List<Movie>>

    @Query("DELETE FROM ${Tables.FAVORITE_MOVIES} WHERE imdbID = :imdbID")
    fun removeFavorite(imdbID: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieTicket(movieTicket: MovieTicket)

    @Query("DELETE FROM ${Tables.MOVIE_TICKETS} WHERE imdbID = :imdbID")
    fun removeMovieTicket(imdbID: String)

    @Query("SELECT * FROM ${Tables.MOVIE_TICKETS}")
    fun getMovieTickets(): LiveData<List<MovieTicket>>

    @Query("SELECT * FROM ${Tables.MOVIE_TICKETS} WHERE imdbID = :imdbID")
    fun getMovieTicketFromId(imdbID: String): MovieTicket
}