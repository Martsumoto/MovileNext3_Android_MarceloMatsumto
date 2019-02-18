package com.marcelokmats.movilenext3_android_marcelomatsumto.movieDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieDetail
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieRetriever

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRetriever = MovieRetriever()
    val movieDetailsLive : LiveData<MovieDetail>
    val movieBaseInfoLive : MutableLiveData<Movie> = MutableLiveData()

    init {
        movieDetailsLive = Transformations.switchMap(movieBaseInfoLive) {
            movieRetriever.getMovieDetail(it.imdbID)}
    }
}