package com.android.movieapp

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.movieapp.adapter.MoviesAdapter
import com.android.movieapp.model.Movie
import com.android.movieapp.mvp.MoviesPresenter
import com.android.movieapp.mvp.MoviesView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MoviesView {

    @InjectPresenter
    lateinit var moviesPresenter: MoviesPresenter


    var adapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mvpDelegate.onCreate()
        initRecyclerView()
        //  moviesPresenter.loadMovies(0)
    }


    override fun showMovies(movies: MutableList<Movie>) {
        recyclerView.visibility = View.VISIBLE
        progress.visibility = View.GONE
        error.visibility = View.GONE

        adapter?.addMovies(movies)
    }

    override fun showErrorView() {
        recyclerView.visibility = View.GONE
        progress.visibility = View.GONE
        error.visibility = View.VISIBLE
    }

    override fun showProgress() {
        recyclerView.visibility = View.GONE
        progress.visibility = View.VISIBLE
        error.visibility = View.GONE
    }

    private fun initRecyclerView() {
        adapter = MoviesAdapter()
        recyclerView.setAdapter(adapter)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager


        var loading = true
        var pastVisibleItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    if (loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            loading = false
                            moviesPresenter.loadMovies()
                        }
                    }
                }
            }
        })

    }

}
