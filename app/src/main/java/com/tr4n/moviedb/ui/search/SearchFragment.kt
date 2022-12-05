package com.tr4n.moviedb.ui.search

import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    private val viewModel : SearchViewModel by viewModel()

    override fun initData() {
        //TODO("Not yet implemented")
        textSearch.text = activity?.editSearch?.text
    }

    override fun listenEvents() {
        //TODO("Not yet implemented")
    }

    override fun observeData() {
        //TODO("Not yet implemented")
    }

    override fun setupViews() {
        //TODO("Not yet implemented")
    }
}
