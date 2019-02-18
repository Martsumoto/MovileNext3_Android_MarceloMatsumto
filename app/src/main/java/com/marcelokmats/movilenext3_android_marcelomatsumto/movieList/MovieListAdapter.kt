package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcelokmats.movilenext3_android_marcelomatsumto.R
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.ImageUtil
import kotlinx.android.synthetic.main.fragment_movielist_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [MovieList] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MovieListAdapter(
    private val mValues: List<Movie>,
    private val mContext: Context,
    private val mListener: (Movie) -> Unit,
    private val mFavoriteListener: (Movie, Boolean) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_movielist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.bindView(item, mListener, mFavoriteListener)
    }

    override fun getItemCount(): Int = mValues.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            item: Movie,
            listener: (Movie) -> Unit,
            favoriteListener: (Movie, Boolean) -> Unit) = with(itemView) {
            ImageUtil.loadImage(mContext, item.Poster?:"", ivMovieCover)

            tvTitle.text = item.Title ?: ""
            tvYear.text = item.Year ?: ""
            icFavorite.setOnFavoriteStateChangedListener{ favoriteListener(item, icFavorite.isActive) }

            setOnClickListener { listener(item) }
        }

    }
}
