package com.tantawii.ahbarkapp.presentation.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

import com.tantawii.ahbarkapp.domain.model.Article

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
){

}
