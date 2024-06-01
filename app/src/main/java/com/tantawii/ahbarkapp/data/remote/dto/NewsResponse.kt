package com.tantawii.ahbarkapp.data.remote.dto

import com.tantawii.ahbarkapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)