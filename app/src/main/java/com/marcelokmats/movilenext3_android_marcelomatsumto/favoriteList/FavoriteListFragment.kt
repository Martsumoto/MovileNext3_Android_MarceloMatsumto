package com.marcelokmats.movilenext3_android_marcelomatsumto.favoriteList

import android.content.Context
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
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.jetbrains.anko.longToast

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [FavoriteListFragment.OnListFragmentInteractionListener] interface.
 */
class FavoriteListFragment : Fragment() {

    companion object {
        fun newInstance(): FavoriteListFragment {
            return FavoriteListFragment()
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
        mMovieViewModel.favoriteListLive.observe(this, Observer { loadMovieList(it) } )
    }

    private fun loadMovieList(movies: List<Movie>?) {
        movies?.let {movies ->
            recyclerView.adapter = MovieListAdapter(
                movies,
                this.context!!, {
                    this.context?.longToast("${it.Title} ...")
                }, { movie: Movie, isActive: Boolean ->
                }
            )
        }

        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
    }
}
