package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieTicket
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.MovieList
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.adapter.MovieTicketListAdapter
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.ViewUtil
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MovieTicketsListFragment.OnListFragmentInteractionListener] interface.
 */
class MovieTicketsListFragment : MovieList() {

    companion object {
        fun newInstance(): MovieTicketsListFragment {
            return MovieTicketsListFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        changeViewType(ViewUtil.Type.PROGRESSBAR)
        mMovieViewModel.movieTicketListLive.observe(this, Observer { loadTicketsList(it) } )
    }

    protected fun loadTicketsList(movies: List<MovieTicket>?) {
        movies?.let { movies ->
            recyclerView.adapter =
                MovieTicketListAdapter(movies,
                    this.context!!,
                    movieTicketClickListener())
            changeViewType(ViewUtil.Type.CONTENT)
        } ?: run {
            changeViewType(ViewUtil.Type.ERROR)
        }

        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
    }
}
