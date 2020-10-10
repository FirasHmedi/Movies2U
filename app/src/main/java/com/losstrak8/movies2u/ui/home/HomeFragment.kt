package com.losstrak8.movies2u.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.losstrak8.movies2u.R
import com.losstrak8.movies2u.data.repository.NetworkState
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val movieAdapter = this.context?.let { PopularMoviePagedListAdapter(it) }

        val rvMovieListView = root.findViewById(R.id.rv_movie_list) as RecyclerView
        rvMovieListView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        rvMovieListView.setHasFixedSize(true)
        rvMovieListView.adapter = movieAdapter

        homeViewModel.moviePagedList.observe(this, Observer {
            movieAdapter?.submitList(it)
        })

        homeViewModel.networkState.observe(this, Observer {
            progress_bar_popular.visibility = if (homeViewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error_popular.visibility = if (homeViewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!homeViewModel.listIsEmpty()) {
                movieAdapter?.setNetworkState(it)
            }
        })

        return root
    }

}