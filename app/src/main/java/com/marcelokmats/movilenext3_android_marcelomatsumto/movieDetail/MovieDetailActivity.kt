package com.marcelokmats.movilenext3_android_marcelomatsumto.movieDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.marcelokmats.movilenext3_android_marcelomatsumto.R
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.MovieListFragment

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mMovieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        mMovieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        mMovieDetailViewModel.movieBaseInfoLive.value = intent.getParcelableExtra(MovieListFragment.MOVIE_DETAIL)
    }
}
