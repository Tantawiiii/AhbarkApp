package com.tantawii.ahbarkapp.presentation.bookmark.data

import com.tantawii.ahbarkapp.domain.model.Article

data class BookmarkState (
    val article: List<Article> = emptyList(),
)
