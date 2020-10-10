package com.losstrak8.movies2u.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.losstrak8.movies2u.data.api.TheMovieDBClient
import com.losstrak8.movies2u.data.api.TheMovieDBInterface
import com.losstrak8.movies2u.data.repository.MovieDetailsNetworkDataSource
import com.losstrak8.movies2u.data.repository.NetworkState
import com.losstrak8.movies2u.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository {

    private val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()
    private lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }



}