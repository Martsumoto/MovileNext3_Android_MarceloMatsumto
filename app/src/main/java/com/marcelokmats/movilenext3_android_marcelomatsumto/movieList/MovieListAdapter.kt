package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelokmats.movilenext3_android_marcelomatsumto.R
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import kotlinx.android.synthetic.main.fragment_movielist_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [MovieList] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MovieListAdapter(
    private val mValues: List<Movie>,
    private val mContext: Context,
    private val mListener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_movielist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.bindView(item, mListener)
    }

    override fun getItemCount(): Int = mValues.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            item: Movie,
            listener: (Movie) -> Unit) = with(itemView) {
            Glide.with(mContext)
                .load(item.posterUrl)
                .into(ivMovieCover)

            tvTitle.text = item.title ?: ""
            tvYear.text = item.year?.toString() ?: ""

            setOnClickListener { listener(item) }
        }

    }
}
