package com.losstrak8.movies2u.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.losstrak8.movies2u.data.api.TheMovieDBClient
import com.losstrak8.movies2u.data.api.TheMovieDBInterface
import com.losstrak8.movies2u.data.repository.NetworkState
import com.losstrak8.movies2u.data.vo.Movie
import com.losstrak8.movies2u.data.vo.MovieResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var moviesRepository: MoviesRepository = MoviesRepository()

    private val isLoadingLive: MutableLiveData<Boolean> = MutableLiveData()
    private val hasErrorLive: MutableLiveData<Boolean> = MutableLiveData()

    val popularMoviesLive: MutableLiveData<List<Movie>> = MutableLiveData()
    val topRatedMoviesLive: MutableLiveData<List<Movie>> = MutableLiveData()
    val latestMoviesLive: MutableLiveData<List<Movie>> = MutableLiveData()

    /*val  moviePopularPagedList : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val  movieTopRatedPagedList : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val  networkState : LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }*/

    fun loadMovies() {
        //isLoadingLive.postValue(true)

        compositeDisposable.add(moviesRepository.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    popularMoviesLive.postValue(it.movieList)
                    //networkState.postValue(NetworkState.LOADED)
                },
                {
                    //networkState.postValue(NetworkState.ERROR)
                    Log.e("MovieDataSource", it.message)
                }
            ))
        compositeDisposable.add(moviesRepository.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    topRatedMoviesLive.postValue(it.movieList)
                    //networkState.postValue(NetworkState.LOADED)
                },
                {
                    //networkState.postValue(NetworkState.ERROR)
                    Log.e("MovieDataSource", it.message)
                }
            ))
        compositeDisposable.add(moviesRepository.getLatestMovies()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    latestMoviesLive.postValue(it.movieList)
                    //networkState.postValue(NetworkState.LOADED)
                },
                {
                    //networkState.postValue(NetworkState.ERROR)
                    Log.e("MovieDataSource", it.message)
                }
            ))

    }

    /*fun listIsEmpty(): Boolean {
        return moviePopularPagedList.value?.isEmpty() ?: true
    }*/


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}