package com.marcelokmats.movilenext3_android_marcelomatsumto.movieDetail

import android.app.Application
import androidx.lifecycle.*
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieDetail
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieRetriever
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieTicket
import com.marcelokmats.movilenext3_android_marcelomatsumto.repository.MovieRepository
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.observeOnce

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRetriever = MovieRetriever()
    private val repository = MovieRepository(application)
    val movieDetailsLive : LiveData<MovieDetail>
    val movieBaseInfoLive : MutableLiveData<Movie> = MutableLiveData()
    val movieTicketLive : LiveData<MovieTicket>
    lateinit var ticket : MovieTicket
        private set
    private lateinit var movie : Movie

    init {
        movieDetailsLive = Transformations.switchMap(movieBaseInfoLive) {
            movieRetriever.getMovieDetail(it.imdbID)}
        movieTicketLive = Transformations.switchMap(movieBaseInfoLive) {
            repository.getTicketFromId(it.imdbID)}

        movieTicketLive.observeOnce(
            Observer { this.ticket = it ?: MovieTicket(this.movie, 0)})
    }

    fun updateTicket(amount: Int) {
        if (amount > 0) {
            repository.insertTicket(MovieTicket(this.movie, amount))
        } else {
            repository.removeTicket(this.movie.imdbID)
        }

        ticket.amount = amount
    }

    fun setMovie(movie: Movie) {
        this.movie = movie
        movieBaseInfoLive.value = movie
    }
}