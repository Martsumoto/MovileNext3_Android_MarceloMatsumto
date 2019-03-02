package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelokmats.movilenext3_android_marcelomatsumto.R
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieTicket
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieDetail.MovieDetailActivity
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.adapter.MovieListAdapter
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.ViewUtil
import kotlinx.android.synthetic.main.fragment_movie_list.*

abstract class MovieList : Fragment() {

    companion object {
        const val MOVIE_DETAIL = "MOVIE_DETAIL"
        const val MOVIE_TITLE = "MOVIE_TITLE"
        const val MOVIE_DETAIL_RESULT = 1
    }

    protected val mMovieViewModel by lazy {
        activity?.let { ViewModelProviders.of(it).get(MovieListViewModel::class.java) }
            ?: run { ViewModelProviders.of(this).get(MovieListViewModel::class.java) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        return view
    }

    private fun startMovieDetailActivity(movie: Movie) {
        val intent = Intent(this.activity, MovieDetailActivity::class.java)

        intent.putExtra(MOVIE_DETAIL, movie)
        intent.putExtra(MOVIE_TITLE, movie.Title)
        startActivityForResult(intent, MOVIE_DETAIL_RESULT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MOVIE_DETAIL_RESULT) {
            data?.let {
                mMovieViewModel.updateMovieTicket(data.getParcelableExtra(MovieDetailActivity.TICKET))
            }

        }
    }

    protected fun loadMovieList(movies: List<Movie>?) {
        movies?.let { movies ->
            recyclerView.adapter =
                MovieListAdapter(movies,
                    this.context!!,
                    movieClickListener(),
                    favoriteClickListener(),
                    mMovieViewModel)

            changeViewType(ViewUtil.Type.CONTENT)
        } ?: run {
            changeViewType(ViewUtil.Type.ERROR)
        }

        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
    }

    private fun movieClickListener(): (Movie) -> Unit {
        return {
            startMovieDetailActivity(it)

        }
    }

    protected fun movieTicketClickListener(): (MovieTicket) -> Unit {
        return {
            startMovieDetailActivity(mMovieViewModel.createMovieFromTicket(it))
        }
    }

    private fun favoriteClickListener(): (Movie, Boolean) -> Unit {
        return { movie, isActive ->
            if (isActive) {
                mMovieViewModel.insertFavorite(movie)
            } else {
                mMovieViewModel.removeFavorite(movie)
            }
        }
    }

    protected fun changeViewType(type : ViewUtil.Type) {
        ViewUtil.changeViewMode(recyclerView, progressBar, tvErrorMessage, type)
    }

}