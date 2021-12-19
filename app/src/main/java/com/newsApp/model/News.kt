package com.newsApp.model


import com.google.gson.annotations.SerializedName
import com.newsApp.model.Article

data class news(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)

