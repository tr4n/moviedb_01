package com.tr4n.moviedb.ui.home

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.ui.detail.MovieDetailFragment
import com.tr4n.moviedb.utils.Constant
import kotlinx.android.synthetic.main.fragment_tab_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TabMovieFragment : BaseFragment(R.layout.fragment_tab_movie) {
    private val viewModel : TabMovieViewModel by viewModel()
    private val tabMovieAdapter = MovieAdapter()
    private var tabMovie = ""
    private var currentPage = 1
    private var isLoading = false

    override fun initData() {
        currentPage = 1
        arguments?.takeIf {
            it.containsKey(Constant.ARGUMENT_KEY_TAB)
        }?.apply {
            when(getInt(Constant.ARGUMENT_KEY_TAB)) {
                HomeTab.NOW_PLAYING.position -> {
                    tabMovie = HomeTab.NOW_PLAYING.tabNameRes
                }
                HomeTab.UPCOMING.position -> {
                    tabMovie = HomeTab.UPCOMING.tabNameRes
                }
                HomeTab.TOP_RATED.position -> {
                    tabMovie = HomeTab.TOP_RATED.tabNameRes
                }
                HomeTab.POPULAR.position -> {
                    tabMovie = HomeTab.POPULAR.tabNameRes
                }
            }
            viewModel.getTabMovie(tabMovie, currentPage)
        }
    }

    override fun listenEvents() {

        recyclerViewTabMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
                val total = tabMovieAdapter.itemCount
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (dy > 0 && !isLoading && (visibleItemCount + pastVisibleItem) >= total) {
                    isLoading = true
                    currentPage++
                    viewModel.getNextPageTabMovie(HomeTab.NOW_PLAYING.tabNameRes,
                        currentPage)
                }
            }
        })

        tabMovieAdapter.onItemSelected = { movieId ->
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.container, MovieDetailFragment.newInstance(movieId))
                ?.addToBackStack(null)
                ?.commit()
        }

    }

    override fun observeData() {
        viewModel.listMoviesTabName.observe(viewLifecycleOwner) {
            isLoading = false
            progressTabMovieBelow.isVisible = it.isEmpty()
            tabMovieAdapter.submitList(it)
        }
        viewModel.exception.observe(viewLifecycleOwner) {
            context?.run {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setupViews() {
        recyclerViewTabMovie.adapter = tabMovieAdapter
    }

}
