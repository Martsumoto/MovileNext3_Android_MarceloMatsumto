package com.marcelokmats.movilenext3_android_marcelomatsumto.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieTicket
import com.marcelokmats.movilenext3_android_marcelomatsumto.dao.MovieDao
import com.marcelokmats.movilenext3_android_marcelomatsumto.db.MovieRoomDatabase
import org.jetbrains.anko.doAsync

class MovieRepository(application: Application) {
    private val movieDao: MovieDao

    val favoritedMovies: LiveData<List<Movie>>
    val movieTickets: LiveData<List<MovieTicket>>

    init {
        val db = MovieRoomDatabase.getDatabase(application)
        movieDao = db.movieDao()
        favoritedMovies = movieDao.getFavorites()
        movieTickets = movieDao.getMovieTickets()
    }

    fun insertFavorite(favorite: Movie) {
        doAsync {
            movieDao.insertFavorite(favorite)
        }
    }

    fun removeFavorite(favorite: Movie) {
        doAsync {
            movieDao.removeFavorite(favorite.imdbID)
        }
    }

    fun insertTicket(ticket: MovieTicket) {
        doAsync {
            movieDao.insertMovieTicket(ticket)
        }
    }

    fun removeTicket(imdbID: String) {
        doAsync {
            movieDao.removeMovieTicket(imdbID)
        }
    }
}