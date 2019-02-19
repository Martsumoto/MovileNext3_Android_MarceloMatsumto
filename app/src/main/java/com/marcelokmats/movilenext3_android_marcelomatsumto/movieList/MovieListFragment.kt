package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelokmats.movilenext3_android_marcelomatsumto.MovieViewModel
import com.marcelokmats.movilenext3_android_marcelomatsumto.R
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieDetail.MovieDetailActivity
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.jetbrains.anko.longToast

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MovieListFragment.OnListFragmentInteractionListener] interface.
 */
class MovieListFragment : Fragment() {

    companion object {
        const val MOVIE_DETAIL = "MOVIE_DETAIL"
        const val MOVIE_TITLE = "MOVIE_TITLE"
        const val MOVIE_DETAIL_RESULT = 1
        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }
    }

    private val mMovieViewModel by lazy {
        activity?.let { ViewModelProviders.of(it).get(MovieViewModel::class.java) }
            ?: run { ViewModelProviders.of(this).get(MovieViewModel::class.java) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        mMovieViewModel.setSearchTextView("lake")

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onResume() {
        super.onResume()
        mMovieViewModel.movieSearchResultLive.observe(this, Observer { loadMovieList(it?.movies) } )
    }

    private fun loadMovieList(movies: List<Movie>?) {
        movies?.let {movies ->
            recyclerView.adapter = MovieListAdapter(
                movies,
                this.context!!, {
                    this.context?.longToast("${it.Title} ...")
                    startMovieDetailActivity(it)

                }, { movie: Movie, isActive: Boolean ->
                    if (isActive) {
                        mMovieViewModel.insertFavorite(movie)
                    } else {
                        mMovieViewModel.removeFavorite(movie)
                    }
                }
            )
        }

        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
    }

    private fun startMovieDetailActivity(movie: Movie) {
        val intent = Intent(this.activity, MovieDetailActivity::class.java)

        intent.putExtra(MOVIE_DETAIL, movie)
        intent.putExtra(MOVIE_TITLE, movie.Title)
        startActivityForResult(intent, MOVIE_DETAIL_RESULT)
    }

}
