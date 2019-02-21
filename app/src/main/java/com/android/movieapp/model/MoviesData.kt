package com.android.movieapp.model

import com.android.movieapp.model.Movie

data class MoviesData(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)