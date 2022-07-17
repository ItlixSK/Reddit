package com.example.reddit.model

import com.google.gson.annotations.SerializedName

data class ModelPost(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("created_utc")
    val date: Long,
    @SerializedName("thumbnail")
    val thumbnailUrl: String,
    @SerializedName("url_overridden_by_dest")
    val bigImageUrl: String,
    @SerializedName("num_comments")
    val commentCount: Int
)
