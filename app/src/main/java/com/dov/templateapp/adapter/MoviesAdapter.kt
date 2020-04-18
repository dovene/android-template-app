package com.dov.templateapp.adapter

import androidx.paging.PagedListAdapter
import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dov.templateapp.R
import com.dov.templateapp.model.Movie
import getImageUrl

class MoviesAdapter(val context: Context, val callback: (Movie) -> Unit
) : PagedListAdapter<Movie, MoviesAdapter.ViewHolder>(MoviesAdapter.DIFF_CALLBACK) {

    companion object {
        const val TYPE_CLASSIC = 0
        const val TYPE_MAIN = 1
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Movie>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldConcert: Movie,
                                         newConcert: Movie) = oldConcert.id == newConcert.id
            override fun areContentsTheSame(oldConcert: Movie,
                                            newConcert: Movie) = oldConcert == newConcert
        }
    }


    override fun getItemViewType(position: Int): Int {
        return TYPE_CLASSIC
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie : Movie? = getItem(position)
        holder.titleTV?.text = movie?.title
        holder.descriptionTV?.text = movie?.overview
        holder.releaseTV?.text = movie?.release_date

        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()


        Glide.with(holder.imageTV?.context!!)
            .load(movie?.getImageUrl(holder.imageTV?.context!!))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(circularProgressDrawable)
            .into(holder.imageTV)
        holder.itemHolder.setOnClickListener {
            callback(movie!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV = itemView.findViewById<AppCompatTextView?>(R.id.titleTV)
        val releaseTV = itemView.findViewById<AppCompatTextView?>(R.id.releaseDateTV)
        val descriptionTV = itemView.findViewById<AppCompatTextView?>(R.id.descriptionTV)
        val imageTV = itemView.findViewById<AppCompatImageView?>(R.id.imageTV)
        val itemHolder = itemView.findViewById<CardView>(R.id.itemHolder)

    }
}