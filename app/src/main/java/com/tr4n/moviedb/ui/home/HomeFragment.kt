package com.tr4n.moviedb.ui.home

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.ui.detail.MovieDetailFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModel()
    private var currentPage = 1
    private var isLoading = false

    private val movieAdapter = MovieAdapter()

    override fun initData() {
        viewModel.getTabMovie(HomeTab.NOW_PLAYING.tabNameRes, currentPage)
        movieAdapter.submitList(viewModel.listMoviesNowPlaying.value)
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        viewPage.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            when (position) {
                HomeTab.NOW_PLAYING.position -> {
                    tab.setText(HomeTab.NOW_PLAYING.tabName)
                }
                HomeTab.UPCOMING.position -> {
                    tab.setText(HomeTab.UPCOMING.tabName)
                }
                HomeTab.TOP_RATED.position -> {
                    tab.setText(HomeTab.TOP_RATED.tabName)
                }
                HomeTab.POPULAR.position -> {
                    tab.setText(HomeTab.POPULAR.tabName)
                }
            }
        }.attach()
    }

    override fun listenEvents() {
        recyclerViewNewlyMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager?.childCount
                val total = movieAdapter.itemCount
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (dx > 0) {
                    if (visibleItemCount != null && !isLoading && (visibleItemCount + pastVisibleItem) >= total) {
                        isLoading = true
                        viewModel.getNextTabMovie(HomeTab.NOW_PLAYING.tabNameRes,
                            currentPage)
                        currentPage++
                    }
                } else if (currentPage > 1 && dx < 0) {
                    if (pastVisibleItem <= visibleItemCount!! && !isLoading) {
                        isLoading = true
                        viewModel.getPreTabMovie(HomeTab.NOW_PLAYING.tabNameRes,
                            currentPage)
                        currentPage--
                    }
                }
            }
        })

        movieAdapter.onItemSelected = { movieId ->
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.container, MovieDetailFragment.newInstance(movieId))
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun observeData() {
        viewModel.listMoviesNowPlaying.observe(viewLifecycleOwner) {
            isLoading = false
            if (it != null) {
                progressNewLyMovie.isVisible = it.isEmpty()
            }
            movieAdapter.submitList(it)
        }
    }

    override fun setupViews() {
        recyclerViewNewlyMovie.adapter = movieAdapter
    }
}
