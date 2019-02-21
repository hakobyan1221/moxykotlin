package com.android.movieapp.mvp

import android.util.Log
import com.android.movieapp.network.NetworkProvider
import com.android.movieapp.network.NetworkService
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MoviesPresenter : MvpPresenter<MoviesView>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val networkService: NetworkService = NetworkProvider.getNetworkService()
    private var page: Int = 1

    fun loadMovies() {

        compositeDisposable.add(networkService.getMovies(page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { moviesData -> Observable.fromIterable(moviesData.results) }
            .filter { movie -> !movie.adult }
            .toList()
            .subscribe(
                { movies ->
                    viewState.showMovies(movies)
                    Log.d("presetner---", movies[0].original_title)
                    page++
                }
                , { e ->
                    run {
                        Log.d("presetner---", e.message)

                        viewState.showErrorView()
                    }
                })
        )


    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.d("presetner---", "attached")
        viewState.showProgress()
        loadMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}