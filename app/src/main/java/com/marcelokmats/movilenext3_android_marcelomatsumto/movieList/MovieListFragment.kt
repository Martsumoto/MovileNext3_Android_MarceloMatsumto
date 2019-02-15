package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList

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
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MovieListFragment.OnListFragmentInteractionListener] interface.
 */
class MovieListFragment : Fragment() {

    companion object {
        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }
    }

    private val mMovieViewModel by lazy {
        ViewModelProviders.of(this).get(MovieViewModel::class.java)
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
        mMovieViewModel.getRepositoriesLiveData().observe(this, Observer { loadMovieList(it.movies) } )
    }

    private fun loadMovieList(movies: List<Movie>) {
        recyclerView.adapter = MovieListAdapter(
            movies,
            this.context!! // TODO
        ) {
            //longToast("${it.title} ...")

            //repositoryRetriever.getLanguageRepositories(
                //callback,
                //it.title)
        }

        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
    }
}
