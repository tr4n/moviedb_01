package com.tr4n.moviedb.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tr4n.moviedb.R
import com.tr4n.moviedb.data.model.Review
import com.tr4n.moviedb.utils.Constant
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter : ListAdapter<Review, ReviewAdapter.ReviewViewHolder>(Review.diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.binData(getItem(position))
    }

    class ReviewViewHolder(
        itemView : View
    ) : RecyclerView.ViewHolder(itemView) {
        private var itemData : Review?= null
        fun binData(review: Review) {
            itemData = review
            itemView.run {
                val avatarPath = if (itemData?.authorDetails?.avatarPath != null) {
                    if ((itemData?.authorDetails?.avatarPath ?: "").contains(Constant.URL_EXTERNAL_AVATAR)) {
                        itemData?.authorDetails?.avatarPath!!.substring(1)
                    } else {
                        Constant.BASE_URL_IMAGE + itemData?.authorDetails?.avatarPath
                    }
                } else {
                    Constant.URL_IMG_NULL
                }

                Glide.with(this)
                    .load(avatarPath)
                    .into(imgAvatarReviewer)

                textRating.text = itemData?.authorDetails?.rating.toString()
                textAuthor.text = itemData?.authorDetails?.userName
                textContent.text = itemData?.content
            }
        }
    }
}
