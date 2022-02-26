package com.example.flixster

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
class MovieAdapter(private val context: Context, private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    // create a view holder of type ViewHolder defined below and return it
    // Expensive operation: create a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    // Cheap: simply bind data to an existing viewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            if(context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                Glide.with(context).load(movie.backdropImageUrl).into(ivPoster)
            }
            else{
                Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
            }
        }

        override fun onClick(v: View?) {
            // 1. Notify the view if it is tapped on
            val movie = movies[adapterPosition]
            // Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
            // 2. Use Intent to navigate to the new screen
            val intent = Intent(context, DetailActivity::class.java)
            // Make movie data parcelable
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}
