package com.tr4n.moviedb.ui.home

import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val movieAdapter = MovieAdapter()

    override fun initData() {
//do nothing
    }

    override fun listenEvents() {
        //do nothing
    }

    override fun observeData() {
        //do nothing
    }

    override fun setupViews() {
        recyclerViewNewlyMovie.adapter = movieAdapter
    }
}
