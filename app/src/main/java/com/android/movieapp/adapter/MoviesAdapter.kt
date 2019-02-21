package com.android.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.movieapp.R
import com.android.movieapp.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movies: MutableList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun addMovies(movies: MutableList<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView = view.title
        private val originalTitle: TextView = view.original_title
        private val voteAverage: TextView = view.vote_average

        fun bind(position: Int) {
            val movie: Movie = movies[position]
            this.title.text = movie.title
            this.originalTitle.text = movie.original_title
            this.voteAverage.text = movie.vote_average.toString()
        }


    }
}