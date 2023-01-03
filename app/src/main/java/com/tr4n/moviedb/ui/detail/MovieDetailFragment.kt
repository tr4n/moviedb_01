package com.tr4n.moviedb.ui.detail

import android.content.Context
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
    private val currentPage = 1
    private val viewModel : MovieDetailViewModel by activityViewModel()

    override fun initData() {
        viewModel.getMovieDetails(movieId)
        viewModel.getAboutMovie()
        viewModel.getMovieReviews(currentPage)
        viewModel.getMoviesSimilar(currentPage)
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
    }

    override fun observeData() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
            Glide.with(this)
                .load(Constant.BASE_URL_IMAGE + movieDetail?.backdropPath)
                .into(imageViewBackdropPathMovieDetail)
            Glide.with(this)
                .load(Constant.BASE_URL_IMAGE + movieDetail?.posterPath)
                .into(imageViewPosterPathMovieDetail)

            textReleaseYearMovieDetail.text = viewModel.getMovieDetailReleaseYear()
            val runtime = movieDetail?.runtime.toString() + context?.resources?.getString(R.string.minutes)
            textRuntimeMovieDetail.text = runtime
            textOriginalTitle.text = movieDetail?.originalTitle
            var movieGenres = ""
            if (movieDetail?.genres != null) {
                for (genre in movieDetail.genres) {
                    movieGenres += genre.name + " "
                }
            }
            textVoteAverage.text = movieDetail.voteAverage.toString()
            textGenreMovieDetail.text = movieGenres
            viewPageMovieDetail.layoutParams.height = (activity?.container?.height ?: 0) - linearLayoutBottom.height
        }
    }

    override fun setupViews() {}

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
