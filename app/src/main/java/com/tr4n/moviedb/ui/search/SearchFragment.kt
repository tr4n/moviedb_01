package com.tr4n.moviedb.ui.search

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.ui.detail.MovieDetailFragment
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    private val viewModel : SearchViewModel by viewModel()
    private val movieSearchAdapter = MovieSearchAdapter()
    private var currentPage = 1
    private var isLoading = false
    private var textQuery = ""

    override fun initData() {
        movieSearchAdapter.submitList(viewModel.listMovieSearchResults.value)
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
                        viewModel.getNextMovieSearchPage(textQuery , currentPage)
                        currentPage++
                    }
                } else if (currentPage > 1 && dy < 0) {
                    if (pastVisibleItem <= visibleItemCount!! && !isLoading) {
                        isLoading = true
                        viewModel.getPreMovieSearchPage(textQuery , currentPage)
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

        editSearch.setOnClickListener {
            currentPage = 1
            textQuery = it.editSearch.text.toString()
            viewModel.getMovieSearchResults(textQuery, currentPage)
        }

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO("Not yet implemented")
                progressBarSearchMovie.visibility = View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.isEmpty() == true) {
                    movieSearchAdapter.submitList(null)
                    imgSearchNotFound.visibility = View.INVISIBLE
                    progressBarSearchMovie.visibility = View.INVISIBLE
                }
            }

        })
    }

    override fun observeData() {
        viewModel.listMovieSearchResults.observe(viewLifecycleOwner) {
            if (it?.isEmpty() == true) {
                imgSearchNotFound.visibility = View.VISIBLE
            } else {
                progressBarSearchMovie.visibility = View.INVISIBLE
            }
            if (it?.isEmpty() == true && textQuery != "") {
                progressBarSearchMovie.isVisible = !imgSearchNotFound.isVisible
            }
            movieSearchAdapter.submitList(it)
            isLoading = false
        }
    }

    override fun setupViews() {
        recyclerViewMovieSearch.adapter = movieSearchAdapter
    }
}
