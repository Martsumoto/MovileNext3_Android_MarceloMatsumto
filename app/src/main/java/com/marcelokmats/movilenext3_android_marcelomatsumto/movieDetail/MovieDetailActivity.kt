package com.marcelokmats.movilenext3_android_marcelomatsumto.movieDetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marcelokmats.movilenext3_android_marcelomatsumto.R
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieDetail
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.MovieList
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.ImageUtil
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.ViewUtil
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val TICKET = "TICKET_AMOUNT"
    }

    private lateinit var mMovieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        mMovieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        changeViewType(ViewUtil.Type.PROGRESSBAR)
        supportActionBar?.title = intent.getStringExtra(MovieList.MOVIE_TITLE)
        mMovieDetailViewModel.setMovie(intent.getParcelableExtra(MovieList.MOVIE_DETAIL))
        mMovieDetailViewModel.movieDetailsLive.observe(this, Observer {
            fillMovieDetails(it)
        })
        mMovieDetailViewModel.movieTicketLive.observe(this, Observer {
            amountSelector.setAmount(it?.amount ?: 0)
        })

        btConfirm.setOnClickListener { confirmTickets() }
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
        ViewUtil.changeViewMode(layoutContent, progressBar, tvErrorMessage, type)
    }

    private fun confirmTickets() {
        val ticketAmount = amountSelector.getAmount()
        mMovieDetailViewModel.updateTicket(ticketAmount)

        intent = Intent()
        intent.putExtra(TICKET, mMovieDetailViewModel.ticket)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
