package com.tr4n.moviedb.ui.home

import android.view.View
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.utils.Constant
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_tab_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TabMovieFragment : BaseFragment(R.layout.fragment_tab_movie) {
    private val viewModel : TabMovieViewModel by viewModel()
    private val tabMovieAdapter = MovieAdapter()
    private var tabMovie = ""
    private var currentPage = 1
    private var currentScrollY = 0
    private var isLoading = false

    override fun initData() {
        viewModel.getTabMovie(Constant.TAB_NOW_PLAYING, Constant.LANGUAGE, 1)
        tabMovieAdapter.submitList(viewModel.listMoviesNowPlaying.value)
    }

    override fun listenEvents() {
        arguments?.takeIf {
            it.containsKey(Constant.ARGUMENT_KEY_TAB)
        }?.apply {
            when(getInt(Constant.ARGUMENT_KEY_TAB)) {
                0-> {
                    tabMovie = Constant.TAB_NOW_PLAYING
                    //viewModel.getTabMovie(Constant.TAB_NOW_PLAYING, Constant.LANGUAGE, 1)
                }
                1-> {
                    tabMovie = Constant.TAB_UPCOMING
                    //viewModel.getTabMovie(Constant.TAB_UPCOMING, Constant.LANGUAGE, 1)
                }
                2-> {
                    tabMovie = Constant.TAB_TOP_RATED
                    //viewModel.getTabMovie(Constant.TAB_TOP_RATED, Constant.LANGUAGE, 1)
                }
                3-> {
                    tabMovie = Constant.TAB_POPULAR
                    //viewModel.getTabMovie(Constant.TAB_POPULAR, Constant.LANGUAGE, 1)
                }
            }
            viewModel.getTabMovie(tabMovie, Constant.LANGUAGE, 1)
        }

        activity?.scrollViewHome?.viewTreeObserver?.addOnScrollChangedListener(OnScrollChangedListener {
            val scrollY: Int? = activity?.scrollViewHome?.scrollY // For ScrollView
            // DO SOMETHING WITH THE SCROLL COORDINATES
            val total = tabMovieAdapter.itemCount
            val layoutManager = recyclerViewTabMovie.layoutManager as GridLayoutManager
            println(total)
            if (scrollY != null) {
                if (scrollY > currentScrollY) {
                    if (!isLoading) {
                        if (scrollY/3942 >= currentPage) {
                            viewModel.getNextTabMovie(tabMovie, Constant.LANGUAGE, currentPage)
                            currentPage++
                            println("up")
                            isLoading = true
                        }
                    }
                } else {
//                    if (!isLoading && currentPage > 1 ) {
//                        //viewModel.getPreTabMovie(tabMovie, Constant.LANGUAGE, currentPage)
//                        currentPage--
//                        isLoading = true
//                        println("down")
//                    }
                }
            }
            if (scrollY != null) {
                currentScrollY = scrollY
            }
        })
    }

    override fun observeData() {
        viewModel.listMoviesNowPlaying.observe(viewLifecycleOwner) {
            isLoading = false
            if (it.isEmpty()) {
                progressTabMovie.visibility = View.VISIBLE
            } else {
                progressTabMovie.visibility = View.INVISIBLE
            }
            tabMovieAdapter.submitList(it)
        }
        viewModel.exception.observe(viewLifecycleOwner) {
            context?.run {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setupViews() {
        recyclerViewTabMovie.layoutManager = GridLayoutManager(context, 3, VERTICAL, false)
        recyclerViewTabMovie.adapter = tabMovieAdapter
    }

}
