package com.tr4n.moviedb.ui.home

import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tab_movie.*


class TabMovieFragment : BaseFragment(R.layout.fragment_tab_movie) {

    private val tabMovieAdapter = MovieAdapter()

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
        recyclerViewTabMovie.adapter = tabMovieAdapter
    }

}
