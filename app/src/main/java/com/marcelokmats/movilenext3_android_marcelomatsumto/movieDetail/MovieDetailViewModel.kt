package com.marcelokmats.movilenext3_android_marcelomatsumto.movieDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieDetail
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieRetriever
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieTicket
import com.marcelokmats.movilenext3_android_marcelomatsumto.repository.MovieRepository

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRetriever = MovieRetriever()
    val movieDetailsLive : LiveData<MovieDetail>
    val movieBaseInfoLive : MutableLiveData<Movie> = MutableLiveData()
    private lateinit var movie : Movie


    private val repository = MovieRepository(application)

    init {
        movieDetailsLive = Transformations.switchMap(movieBaseInfoLive) {
            movieRetriever.getMovieDetail(it.imdbID)}
    }

    fun updateTicket(amount: Int) {
        if (amount > 0) {
            repository.insertTicket(MovieTicket(this.movie, amount))
        } else {
            repository.removeTicket(this.movie.imdbID)
        }
    }

    fun setMovie(movie: Movie) {
        this.movie = movie
        movieBaseInfoLive.value = movie
    }
}