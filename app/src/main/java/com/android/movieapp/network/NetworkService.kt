package com.android.movieapp.network

import com.android.movieapp.model.MoviesData
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query


interface NetworkService {

    @POST("movie/popular")
    fun getMovies(
        //  @Url url: String = NetworkProvider.getPopularMovies,
        //  @Header("Content-Type") contentType: String = "application/json",
        @Query("api_key") key: String = NetworkProvider.api_key,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Observable<MoviesData>

}