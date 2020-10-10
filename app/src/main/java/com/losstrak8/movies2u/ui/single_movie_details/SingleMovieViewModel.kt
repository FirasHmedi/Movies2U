package com.losstrak8.movies2u.ui.single_movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.losstrak8.movies2u.data.api.TheMovieDBClient
import com.losstrak8.movies2u.data.api.TheMovieDBInterface
import com.losstrak8.movies2u.data.repository.NetworkState
import com.losstrak8.movies2u.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel (movieId: Int)  : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var movieRepository: MovieDetailsRepository = MovieDetailsRepository()

    val  movieDetails : LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable,movieId)
    }

    val networkState : LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }



}