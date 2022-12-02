package com.tr4n.moviedb.ui.home

import androidx.recyclerview.widget.GridLayoutManager
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.data.model.Movie
import kotlinx.android.synthetic.main.fragment_tab_movie.*

class TabMovieFragment : BaseFragment(R.layout.fragment_tab_movie) {
    private val tabMovieAdapter = MovieAdapter()

    override fun initData() {
        val movies = listOf(
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg")
        )
        tabMovieAdapter.submitList(movies)
    }

    override fun listenEvents() {
        //TODO("Not yet implemented")
    }

    override fun observeData() {
        //TODO("Not yet implemented")
    }

    override fun setupViews() {
        val layoutManager = GridLayoutManager( activity?.applicationContext, 3)
        recyclerViewTabMovie.layoutManager = layoutManager
        recyclerViewTabMovie.adapter = tabMovieAdapter
    }

}
