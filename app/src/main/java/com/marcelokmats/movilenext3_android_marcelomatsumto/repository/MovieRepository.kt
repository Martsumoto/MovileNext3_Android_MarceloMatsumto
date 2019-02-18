package com.marcelokmats.movilenext3_android_marcelomatsumto.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.dao.MovieDao
import com.marcelokmats.movilenext3_android_marcelomatsumto.db.MovieRoomDatabase
import org.jetbrains.anko.doAsync

class MovieRepository(application: Application) {
    private val movieDao: MovieDao

    val favoritedMovies: LiveData<List<Movie>>

    init {
        val db = MovieRoomDatabase.getDatabase(application)
        movieDao = db.movieDao()
        favoritedMovies = movieDao.getFavorites()
    }

    fun insert(favorite: Movie) {
        doAsync {
            movieDao.insertFavorite(favorite)
        }
    }

    fun remove(favorite: Movie) {
        doAsync {
            movieDao.removeFavorite(favorite.imdbID)
        }
    }
}