package com.tr4n.moviedb.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes private val layoutRes: Int) : Fragment(layoutRes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        initData()
        listenEvents()
        observeData()
    }
    abstract fun initData()
    abstract fun listenEvents()
    abstract fun observeData()
    abstract fun setupViews()
}
