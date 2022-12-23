package com.tr4n.moviedb.ui.detail

import com.tr4n.moviedb.R
import com.tr4n.moviedb.base.BaseFragment
import com.tr4n.moviedb.data.model.AuthorDetails
import com.tr4n.moviedb.data.model.Review
import kotlinx.android.synthetic.main.fragment_tab_reviews.*

class TabReviewsFragment : BaseFragment(R.layout.fragment_tab_reviews) {
    private val reviewAdapter = ReviewAdapter()

    override fun initData() {
        val listReviews = listOf<Review>(
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff"),
            Review("124", AuthorDetails("hung", "hahungg", "/uCmwgSbJAcHqNwSvQvTv2dB95tx.jpg", 4), "hfskjdfhkjhdskjfhjshfkhsdkjfhhffffffffff")
        )

        reviewAdapter.submitList(listReviews)
    }

    override fun listenEvents() {
        //TODO("Not yet implemented")
    }

    override fun observeData() {
        //TODO("Not yet implemented")
    }

    override fun setupViews() {
        recyclerViewReviews.adapter = reviewAdapter
    }

}
