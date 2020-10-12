package com.losstrak8.movies2u.data.api

import com.losstrak8.movies2u.data.vo.MovieDetails
import com.losstrak8.movies2u.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    // https://api.themoviedb.org/3/movie/popular?api_key=6e63c2317fbe963d76c3bdc2b785f6d1&page=1
    // https://api.themoviedb.org/3/movie/299534?api_key=6e63c2317fbe963d76c3bdc2b785f6d1
    // https://api.themoviedb.org/3/

    @GET("movie/popular")
    fun getPopularMovies(/*@Query("page") page: Int*/): Single<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(/*@Query("page") page: Int*/): Single<MovieResponse>

    @GET("movie/now_playing")
    fun getLatestMovies(/*@Query("page") page: Int*/): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}