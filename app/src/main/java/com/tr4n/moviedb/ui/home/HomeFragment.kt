package com.tr4n.moviedb.ui.home

import com.google.android.material.tabs.TabLayoutMediator
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.data.model.Movie
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel : HomeViewModel by viewModel()

    private val movieAdapter = MovieAdapter()

    override fun initData() {
        val movies = listOf( Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"),
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
            Movie(234, "https://lumiere-a.akamaihd.net/v1/images/p_aladdin2019_17638_d53b09e6.jpeg"))
        movieAdapter.submitList(movies)
    }

    override fun listenEvents() {
        //TODO("Not yet implemented")
        val viewPagerAdapter =
            activity?.supportFragmentManager?.let { ViewPagerAdapter(it, lifecycle) }
        viewPage.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            when(position) {
                0-> {
                    tab.setText(R.string.name_tab_now_playing)
                }
                1-> {
                    tab.setText(R.string.name_tab_upcoming)
                }
                2-> {
                    tab.setText(R.string.name_tab_top_rated)
                }
                3-> {
                    tab.setText(R.string.name_tab_popular)
                }
            }
        }.attach()
    }

    override fun observeData() {
        //TODO("Not yet implemented")
    }

    override fun setupViews() {
        recyclerViewNewlyMovie.adapter = movieAdapter
    }
}
