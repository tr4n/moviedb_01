package com.tr4n.moviedb.ui.detail

import android.content.Context
import android.widget.Toast
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.utils.Constant
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {
    private var movieId = 0L
    private var currentPage = 1
    private val viewModel : MovieDetailViewModel by activityViewModel()

    override fun initData() {
        currentPage = 1
        viewModel.getMovieDetails(movieId)
        viewModel.getAboutMovie()
        viewModel.getMovieReviews(currentPage)
        viewModel.getMoviesSimilar(currentPage)
        viewModel.getWatchListById(movieId)
        if(viewModel.movieWatchList.value != null) {
            checkboxSaveMovie.isChecked = true
        }
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.movieId = movieId
        viewPageMovieDetail.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPageMovieDetail) { tab, position ->
            when(position) {
                DetailTab.ABOUT_MOVIE.position -> {
                    tab.setText(DetailTab.ABOUT_MOVIE.tabName)
                }
                DetailTab.REVIEWS.position -> {
                    tab.setText(DetailTab.REVIEWS.tabName)
                }
                DetailTab.SIMILAR.position -> {
                    tab.setText(DetailTab.SIMILAR.tabName)
                }
            }
        }.attach()
    }

    override fun listenEvents() {
        buttonBackFromDetail.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        checkboxSaveMovie.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.movieDetail.value?.let { movie->
                    viewModel.insertWatchList(movie)
                }
            } else {
                viewModel.deleteWatchListById(movieId)
            }
        }
    }

    override fun observeData() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
            if(!movieDetail?.backdropPath.isNullOrEmpty()) {
                Glide.with(this)
                    .load(Constant.BASE_URL_IMAGE + movieDetail.backdropPath)
                    .into(imageViewBackdropPathMovieDetail)
            } else {
                movieDetail.backdropPath = ""
                imageViewBackdropPathMovieDetail.setImageResource(R.drawable.bg_image_not_found)
            }

            if (!movieDetail?.posterPath.isNullOrEmpty()) {
                Glide.with(this)
                    .load(Constant.BASE_URL_IMAGE + movieDetail.posterPath)
                    .into(imageViewPosterPathMovieDetail)
            } else {
                movieDetail.posterPath = ""
                imageViewPosterPathMovieDetail.setImageResource(R.drawable.bg_image_not_found)
            }

            textReleaseYearMovieDetail.text = viewModel.getMovieDetailReleaseYear()
            val runtime = movieDetail?.runtime.toString() + context?.resources?.getString(R.string.minutes)
            textRuntimeMovieDetail.text = runtime
            textOriginalTitle.text = movieDetail?.originalTitle
            var movieGenres = ""
            val genres = movieDetail?.genres ?: emptyList()
            for (genre in genres) {
                movieGenres += genre.name + " "
            }
            textVoteAverage.text = movieDetail.voteAverage.toString()
            textGenreMovieDetail.text = movieGenres
            val movieDetailFragmentActivity = activity
            if (movieDetailFragmentActivity != null) {
                movieDetailFragmentActivity.viewPageMovieDetail.layoutParams.height = movieDetailFragmentActivity.container.height -
                        movieDetailFragmentActivity.linearLayoutBottom.height - movieDetailFragmentActivity.linearLayout.height -
                        movieDetailFragmentActivity.linearLayout.paddingBottom
            }
        }
        viewModel.exception.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        }

    }

    override fun setupViews() {
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
