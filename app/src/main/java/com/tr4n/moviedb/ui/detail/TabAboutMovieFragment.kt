package com.tr4n.moviedb.ui.detail

import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tab_about_movie.*
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class TabAboutMovieFragment : BaseFragment(R.layout.fragment_tab_about_movie) {
    private val viewModel : MovieDetailViewModel by activityViewModel()

    override fun initData() {
        //TODO("Not yet implemented")
    }

    override fun listenEvents() {
        //TODO("Not yet implemented")
    }

    override fun observeData() {
        viewModel.txtAboutMovie.observe(requireParentFragment()) { aboutMovie ->
            textAboutMovie.text = aboutMovie
        }
    }

    override fun setupViews() {
        //TODO("Not yet implemented")
    }

}
