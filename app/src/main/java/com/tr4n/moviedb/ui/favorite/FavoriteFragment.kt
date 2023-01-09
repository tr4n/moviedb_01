package com.tr4n.moviedb.ui.favorite

import android.widget.Toast
import androidx.core.view.isVisible
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.ui.detail.MovieDetailFragment
import com.tr4n.moviedb.ui.detail.MovieDetailViewModel
import com.tr4n.moviedb.ui.search.MovieSearchAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {
    private val viewModel : MovieDetailViewModel by activityViewModel()
    private val movieSearchAdapter = MovieSearchAdapter()

    override fun initData() {
        viewModel.getListMovieWatchList()
    }

    override fun listenEvents() {
        movieSearchAdapter.onItemSelected = { movieId ->
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.container, MovieDetailFragment.newInstance(movieId))
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun observeData() {
        viewModel.listMovieWatchList.observe(viewLifecycleOwner) {
            movieSearchAdapter.submitList(it)
            imgNoMovieInDb.isVisible = it.isEmpty()
        }
        viewModel.exception.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun setupViews() {
        recyclerViewWatchList.adapter = movieSearchAdapter
    }

}
