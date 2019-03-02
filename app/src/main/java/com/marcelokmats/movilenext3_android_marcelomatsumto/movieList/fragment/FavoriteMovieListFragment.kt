package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.fragment

import androidx.lifecycle.Observer
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.MovieList
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.ViewUtil

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
        changeViewType(ViewUtil.Type.PROGRESSBAR)
        mMovieViewModel.favoriteListLive.observe(this, Observer { loadMovieList(it) } )
    }
}
