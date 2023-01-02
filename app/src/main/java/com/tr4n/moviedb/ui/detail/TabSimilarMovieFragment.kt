package com.tr4n.moviedb.ui.detail

import android.content.Context
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.ui.home.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_tab_similar_movie.*
import kotlinx.android.synthetic.main.fragment_tab_similar_movie.view.*
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class TabSimilarMovieFragment : BaseFragment(R.layout.fragment_tab_similar_movie) {
    private val viewModel by activityViewModel<MovieDetailViewModel>()
    private var listMovieSimilar = listOf<Movie>()
    private val movieSimilarAdapter = MovieAdapter()
    private var movieId = 0L
    private var currentPage = 2
    private var isLoading = false

    override fun initData() {
        movieSimilarAdapter.submitList(viewModel.listMovieSimilar.value)
    }

    override fun listenEvents() {
        activity?.nestedScrollViewMovieDetail?.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).height - v.height) {
                v.recyclerViewSimilarMovie?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
                        val total = movieSimilarAdapter.itemCount
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                        if (dy > 0 && !isLoading && (visibleItemCount + pastVisibleItem) >= total) {
                            isLoading = true
                            viewModel.getMoviesSimilar(movieId, currentPage)
                            currentPage++
                        }
                    }
                })
            }
        })
        movieSimilarAdapter.onItemSelected = { movieId ->
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MovieDetailFragment.newInstance(movieId))
                ?.addToBackStack(null)
                ?.commit()
        }

    }

    override fun observeData() {
        viewModel.listMovieSimilar.observe(viewLifecycleOwner) {
            isLoading = false
            listMovieSimilar = listMovieSimilar.plus(it)
            movieSimilarAdapter.submitList(listMovieSimilar)
        }
        viewModel.exception.observe(viewLifecycleOwner) {
            context?.run {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setupViews() {
        recyclerViewSimilarMovie.adapter = movieSimilarAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getLong(BUNDLE_MOVIE_ID)?.let {
            movieId = it
        }
    }

    companion object {
        private const val BUNDLE_MOVIE_ID = "BUNDLE_MOVIE_ID"

        fun newInstance(movieId : Long) : TabSimilarMovieFragment {
            val tabSimilarMovieFragment = TabSimilarMovieFragment().apply {
                arguments = bundleOf(BUNDLE_MOVIE_ID to movieId)
            }
            return tabSimilarMovieFragment
        }
    }

}
