package com.android.movieapp.mvp

import com.android.movieapp.model.Movie
import com.arellomobile.mvp.MvpView

interface MoviesView : MvpView {
    fun showMovies(movies: MutableList<Movie>)
    fun showErrorView()
    fun showProgress()
}