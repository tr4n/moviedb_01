package com.tr4n.moviedb.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            DetailTab.ABOUT_MOVIE.position -> {
                TabAboutMovieFragment()
            }
            DetailTab.REVIEWS.position -> {
                TabReviewsFragment()
            }
            else -> {
                TabSimilarMovieFragment()
            }
        }
    }

}
