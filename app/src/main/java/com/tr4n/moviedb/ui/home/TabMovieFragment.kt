package com.tr4n.moviedb.ui.home

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.ui.detail.MovieDetailFragment
import com.tr4n.moviedb.utils.Constant
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_tab_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TabMovieFragment : BaseFragment(R.layout.fragment_tab_movie) {
    private val viewModel : TabMovieViewModel by viewModel()
    private val tabMovieAdapter = MovieAdapter()
    private var tabMovie = ""
    private var currentPage = 1
    private var isLoading = false

    override fun initData() {
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
            viewModel.getTabMovie(tabMovie, 1)
        }
    }

    override fun listenEvents() {
        activity?.scrollViewHome?.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == 0 && currentPage > 1) {
                viewModel.getPreTabMovie(tabMovie, currentPage)
                currentPage--
            }
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                viewModel.getNextTabMovie(tabMovie, currentPage)
                currentPage++
            }
        })

        tabMovieAdapter.onItemSelected = { movieId ->
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MovieDetailFragment.newInstance(movieId))
                ?.addToBackStack(null)
                ?.commit()
        }

    }

    override fun observeData() {
        viewModel.listMoviesTabName.observe(viewLifecycleOwner) {
            isLoading = false
            progressTabMovie.isVisible = it.isEmpty()
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
