package com.example.reddit.utils


import android.text.format.DateUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.reddit.R

@BindingAdapter("postImage")
fun ImageView.setImage(thumbnailUrl: String) {
    Glide.with(this.context)
        .load(thumbnailUrl)
        .placeholder(R.drawable.image_placeholder)
        .error(R.drawable.broken_image)
        .centerCrop()
        .into(this)
}

@BindingAdapter("date")
fun TextView.setDate(date: Long) {
    val timeSpan = DateUtils.getRelativeTimeSpanString(date * 1000)
    text = timeSpan.toString()
}

@BindingAdapter("comments")
fun TextView.setComments(commentsCount: Int) {
    val str = "$commentsCount comments"
    text = str
}

@BindingAdapter("author")
fun TextView.setAuthor(author: String) {
    val authorStr = "Posted by $author"
    text = authorStr
}