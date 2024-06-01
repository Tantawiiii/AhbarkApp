package com.tantawii.ahbarkapp.data.remote.dto

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)