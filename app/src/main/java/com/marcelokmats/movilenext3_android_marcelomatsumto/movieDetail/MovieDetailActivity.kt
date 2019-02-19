package com.marcelokmats.movilenext3_android_marcelomatsumto.movieDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marcelokmats.movilenext3_android_marcelomatsumto.R
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieDetail
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.MovieListFragment
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.ImageUtil
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.ViewUtil
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mMovieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        mMovieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        changeViewType(ViewUtil.Type.PROGRESSBAR)
        supportActionBar?.title = intent.getStringExtra(MovieListFragment.MOVIE_TITLE)
        mMovieDetailViewModel.movieBaseInfoLive.value = intent.getParcelableExtra(MovieListFragment.MOVIE_DETAIL)
        mMovieDetailViewModel.movieDetailsLive.observe(this, Observer {
            fillMovieDetails(it)
        })
    }

    private fun fillMovieDetails(movieDetail: MovieDetail) {
        changeViewType(ViewUtil.Type.CONTENT)
        supportActionBar?.subtitle = movieDetail.Year
        tvGenre.text = movieDetail.Genre
        tvRuntime.text = movieDetail.Runtime
        tvDirector.text = movieDetail.Director
        tvWriter.text = movieDetail.Writer
        tvPlot.text = movieDetail.Plot
        ImageUtil.loadImage(this, movieDetail.Poster ?: "", ivPoster)

    }

    private fun changeViewType(type : ViewUtil.Type) {
        ViewUtil.changeViewMode(layoutContent, progressBar, tvErrorMessage, type);
    }
}
