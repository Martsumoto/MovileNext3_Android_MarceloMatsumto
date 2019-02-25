package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList

import androidx.lifecycle.Observer

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [SearchMovieListFragment.OnListFragmentInteractionListener] interface.
 */
class SearchMovieListFragment : MovieList() {

    companion object {
        fun newInstance(): SearchMovieListFragment {
            return SearchMovieListFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        mMovieViewModel.setSearchTextView("lake")
        mMovieViewModel.movieSearchResultLive.observe(this, Observer { loadMovieList(it?.movies) } )
    }

}
