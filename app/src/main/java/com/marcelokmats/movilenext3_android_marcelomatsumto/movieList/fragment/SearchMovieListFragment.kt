package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.fragment

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.MovieList
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.jetbrains.anko.longToast

/**
 * Fragment that allows the user to search for a movie
 */
class SearchMovieListFragment : MovieList() {

    companion object {
        fun newInstance(): SearchMovieListFragment {
            return SearchMovieListFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        setupSearch()
        mMovieViewModel.setSearchTextView("Spider-Man") // First search, as example
        mMovieViewModel.movieSearchResultLive.observe(this, Observer { loadMovieList(it?.movies) } )
        mMovieViewModel.searchTextList.observe(this, Observer { etSearch.setText(it) })
        btClear.setOnClickListener{ etSearch.setText("") }
    }

    private fun setupSearch() {
        etSearch.visibility = View.VISIBLE
        etSearch.setOnEditorActionListener() { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (etSearch.text.toString().length < 3) {
                    activity?.longToast("Type at least the 3 characters")
                } else {
                    mMovieViewModel.setSearchTextView(etSearch.text.toString())
                }
                true
            } else {
                false
            }
        }
    }


}
