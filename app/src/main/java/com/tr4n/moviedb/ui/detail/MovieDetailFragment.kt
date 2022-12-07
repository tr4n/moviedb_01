package com.tr4n.moviedb.ui.detail

import android.content.Context
import android.widget.Toast
import androidx.core.os.bundleOf
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {
    private var movieId = 0L
    override fun initData() {
        //TODO("Not yet implemented")
        Toast.makeText(activity, movieId.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun listenEvents() {
        buttonBackFromDetail.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun observeData() {
        //TODO("Not yet implemented")
    }

    override fun setupViews() {
        //TODO("Not yet implemented")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getLong(BUNDLE_MOVIE_ID)?.let {
            movieId = it
        }
    }

    companion object {
        private const val BUNDLE_MOVIE_ID = "BUNDLE_MOVIE_ID"

        fun newInstance(movieId: Long): MovieDetailFragment {
            val movieDetailFragment = MovieDetailFragment().apply {
                arguments = bundleOf(BUNDLE_MOVIE_ID to movieId)
            }
            return movieDetailFragment
        }
    }
}
