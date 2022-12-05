package com.tr4n.moviedb.ui.detail

import android.view.View
import android.widget.Toast
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*


/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MovieDetailFragment(private val movieId: Long) : BaseFragment(R.layout.fragment_movie_detail) {
    override fun initData() {
        //TODO("Not yet implemented")
        Toast.makeText(activity, movieId.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun listenEvents() {
        buttonBackFromDetail.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
            activity?.navView?.visibility = View.VISIBLE
        }
    }

    override fun observeData() {
        //TODO("Not yet implemented")
    }

    override fun setupViews() {
        //TODO("Not yet implemented")
    }

    companion object {
        fun newInstance(movieId : Long) : MovieDetailFragment {
            return MovieDetailFragment(movieId)
        }
    }
}
