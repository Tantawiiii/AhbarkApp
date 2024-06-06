package com.tantawii.ahbarkapp.domain.repository

import androidx.paging.PagingData
import com.tantawii.ahbarkapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>) : Flow<PagingData<Article>>
    fun searchNews(searchQuery:String ,source: List<String>) : Flow<PagingData<Article>>

}