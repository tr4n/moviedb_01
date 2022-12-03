package com.tr4n.moviedb.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tr4n.moviedb.utils.Constant

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle ) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TabMovieFragment()
        fragment.arguments = Bundle().apply {
            putInt(Constant.ARGUMENT_KEY_TAB, position)
        }
        return fragment
    }

}
