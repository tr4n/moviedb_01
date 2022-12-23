package com.tr4n.moviedb.ui.detail

import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.ui.home.MovieAdapter
import kotlinx.android.synthetic.main.fragment_tab_similar_movie.*

class TabSimilarMovieFragment : BaseFragment(R.layout.fragment_tab_similar_movie) {
    private val movieAdapter = MovieAdapter()

    override fun initData() {
        //ddd
    }

    override fun listenEvents() {
        //TODO("Not yet implemented")
    }

    override fun observeData() {
        //TODO("Not yet implemented")
    }

    override fun setupViews() {
        recyclerViewSimilarMovie.adapter = movieAdapter
    }

}
