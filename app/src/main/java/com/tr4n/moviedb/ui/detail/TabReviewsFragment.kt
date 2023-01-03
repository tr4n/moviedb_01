package com.tr4n.moviedb.ui.detail

import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.data.model.Review
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_tab_reviews.*
import kotlinx.android.synthetic.main.fragment_tab_reviews.view.*
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class TabReviewsFragment : BaseFragment(R.layout.fragment_tab_reviews) {
    private val reviewAdapter = ReviewAdapter()
    private var listReviews = listOf<Review>()
    private var currentPage = 2
    private var isLoading = false
    private val viewModel by activityViewModel<MovieDetailViewModel>()

    override fun initData() {}
    override fun listenEvents() {
        activity?.nestedScrollViewMovieDetail?.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).height - v.height) {
                v.recyclerViewReviews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
                        val total = reviewAdapter.itemCount
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                        if (dx > 0 && !isLoading && (visibleItemCount + pastVisibleItem) >= total) {
                            isLoading = true
                            viewModel.getMovieReviews(currentPage)
                            currentPage++
                        }
                    }
                })
            }
        })
    }

    override fun observeData() {
        viewModel.movieReviews.observe(viewLifecycleOwner) {
            isLoading = false
            listReviews = listReviews.plus(it)
            reviewAdapter.submitList(listReviews)
        }
    }

    override fun setupViews() {
        recyclerViewReviews.adapter = reviewAdapter
    }

}