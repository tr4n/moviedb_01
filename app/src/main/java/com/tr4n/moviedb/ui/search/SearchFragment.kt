package com.tr4n.moviedb.ui.search

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.ui.detail.MovieDetailFragment
import com.tr4n.moviedb.ui.home.HomeTab
import com.tr4n.moviedb.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    private val viewModel : HomeViewModel by viewModel()
    private val movieSearchAdapter = MovieSearchAdapter()
    private var currentPage = 1
    private var isLoading = false

    override fun initData() {
        viewModel.getTabMovie(HomeTab.NOW_PLAYING.tabNameRes, currentPage)
        movieSearchAdapter.submitList(viewModel.listMoviesNowPlaying.value)
    }

    override fun listenEvents() {
        recyclerViewMovieSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager?.childCount
                val total = movieSearchAdapter.itemCount
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (dy > 0) {
                    if (visibleItemCount != null && !isLoading && (visibleItemCount + pastVisibleItem) >= total) {
                        isLoading = true
                        viewModel.getNextTabMovie(HomeTab.NOW_PLAYING.tabNameRes,
                            currentPage)
                        currentPage++
                    }
                } else if (currentPage > 1 && dy < 0) {
                    if (pastVisibleItem <= visibleItemCount!! && !isLoading) {
                        isLoading = true
                        viewModel.getPreTabMovie(HomeTab.NOW_PLAYING.tabNameRes,
                            currentPage)
                        currentPage--
                    }
                }
            }
        })
        movieSearchAdapter.onItemSelected = { movieId ->
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MovieDetailFragment.newInstance(movieId))
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun observeData() {
        viewModel.listMoviesNowPlaying.observe(viewLifecycleOwner) {
            isLoading = false
            if (it != null) {
                //progressNewLyMovie.isVisible = it.isEmpty()
            }
            movieSearchAdapter.submitList(it)
        }
    }

    override fun setupViews() {
        recyclerViewMovieSearch.adapter = movieSearchAdapter
    }
}
