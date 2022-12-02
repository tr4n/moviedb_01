package com.tr4n.moviedb.ui.home

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.databinding.ActivityMainBinding
import com.tr4n.moviedb.utils.Constant
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val viewModel : HomeViewModel by viewModel()
    private val movieAdapter = MovieAdapter()
    private var currentPage = 1
    private var isLoading = false

    override fun initData() {
        viewModel.getRequestToken()
        viewModel.getTabMovie(Constant.TAB_NOW_PLAYING, Constant.LANGUAGE, currentPage)
        movieAdapter.submitList(viewModel.listMoviesNowPlaying.value)
    }

    override fun listenEvents() {
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


        //endless scrolling
        recyclerViewNewlyMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager?.childCount
                val total = movieAdapter.itemCount
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (dx > 0) {
                    if (visibleItemCount != null) {
                        if (!isLoading) {
                            if ((visibleItemCount + pastVisibleItem) >= total) {
                                isLoading = true
                                viewModel.getNextTabMovie(Constant.TAB_NOW_PLAYING, Constant.LANGUAGE, currentPage)
                                currentPage++
                            }
                        }
                    }
                }
                else if (currentPage > 1 && dx < 0) {
                    if (!isLoading) {
                        if (pastVisibleItem <= visibleItemCount!!) {
                            isLoading = true
                            viewModel.getPreTabMovie(Constant.TAB_NOW_PLAYING, Constant.LANGUAGE, currentPage)
                            currentPage--
                        }
                    }
                }
            }
        })

        /// search
        editText_search.setOnClickListener {
            activity?.nav_view?.selectedItemId = R.id.navigation_search
        }
    }

    override fun observeData() {
        viewModel.listMoviesNowPlaying.observe(viewLifecycleOwner) {
            isLoading = false
            if (it?.isEmpty() == true) {
                progressNewLyMovie.visibility = View.VISIBLE
            } else {
                progressNewLyMovie.visibility = View.INVISIBLE
            }
            movieAdapter.submitList(it)
        }
        viewModel.exception.observe(viewLifecycleOwner) {
            context?.run {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setupViews() {
        recyclerViewNewlyMovie.adapter = movieAdapter
    }
}
