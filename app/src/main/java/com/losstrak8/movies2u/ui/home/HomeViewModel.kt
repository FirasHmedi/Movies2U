package com.losstrak8.movies2u.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.losstrak8.movies2u.data.api.TheMovieDBClient
import com.losstrak8.movies2u.data.api.TheMovieDBInterface
import com.losstrak8.movies2u.data.repository.NetworkState
import com.losstrak8.movies2u.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()

    private  var movieRepository: MoviePagedListRepository = MoviePagedListRepository(apiService)

    val  moviePagedList : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val  networkState : LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}