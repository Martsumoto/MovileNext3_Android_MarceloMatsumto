package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList

import androidx.lifecycle.Observer

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [FavoriteMovieListFragment.OnListFragmentInteractionListener] interface.
 */
class FavoriteMovieListFragment : MovieList() {

    companion object {
        fun newInstance(): FavoriteMovieListFragment {
            return FavoriteMovieListFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        mMovieViewModel.favoriteListLive.observe(this, Observer { loadMovieList(it) } )
    }
}
