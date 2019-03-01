package com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcelokmats.movilenext3_android_marcelomatsumto.R
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieTicket
import com.marcelokmats.movilenext3_android_marcelomatsumto.movieList.MovieListViewModel
import com.marcelokmats.movilenext3_android_marcelomatsumto.util.ImageUtil
import kotlinx.android.synthetic.main.fragment_movieticket_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [MovieTicketList] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MovieTicketListAdapter(
    private val mValues: List<MovieTicket>,
    private val mContext: Context,
    private val mListener: (MovieTicket) -> Unit,
    movieListViewModel: MovieListViewModel
) : RecyclerView.Adapter<MovieTicketListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_movieticket_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.bindView(item, mListener)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            item: MovieTicket,
            listener: (MovieTicket) -> Unit) = with(itemView) {
            ImageUtil.loadImage(mContext, item.Poster?:"", ivMovieCover)

            tvTitle.text = item.Title ?: ""
            tvYear.text = item.Year ?: ""
            tvTickets.text = item.amount.toString()

            setOnClickListener { listener(item) }
        }

    }
}
