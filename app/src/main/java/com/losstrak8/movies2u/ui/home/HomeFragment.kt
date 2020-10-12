package com.losstrak8.movies2u.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.losstrak8.movies2u.R
import com.losstrak8.movies2u.data.vo.Movie

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var currentConx: Context
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val rvMovieListView1 = root.findViewById(R.id.rv_movie_list1) as RecyclerView
        val rvMovieListView2 = root.findViewById(R.id.rv_movie_list2) as RecyclerView
        val rvMovieListView3 = root.findViewById(R.id.rv_movie_list3) as RecyclerView
        currentConx = context!!
        homeViewModel.loadMovies()

        homeViewModel.latestMoviesLive.observe(this@HomeFragment, Observer {
            setupMoviesAdapter(rvMovieListView1,it)
        })

        homeViewModel.popularMoviesLive.observe(this@HomeFragment, Observer {
            setupMoviesAdapter(rvMovieListView2,it)
        })

        homeViewModel.topRatedMoviesLive.observe(this@HomeFragment, Observer {
            setupMoviesAdapter(rvMovieListView3,it)
        })

        /*homeViewModel.networkState.observe(this, Observer {
            progress_bar_popular.visibility = if (homeViewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error_popular.visibility = if (homeViewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!homeViewModel.listIsEmpty()) {
                popularMovieAdapter?.setNetworkState(it)
            }
        })*/

        return root
    }

    private fun setupMoviesAdapter(moviesRecycler: RecyclerView, movieList: List<Movie>) {
        moviesRecycler.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(currentConx)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        moviesRecycler.layoutManager = linearLayoutManager
        moviesRecycler.isNestedScrollingEnabled = false
        moviesRecycler.adapter = MoviesAdapter(movieList)
    }

}