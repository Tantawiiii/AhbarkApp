package com.tantawii.ahbarkapp.domain.usecases.news

import androidx.paging.PagingData
import com.tantawii.ahbarkapp.domain.model.Article
import com.tantawii.ahbarkapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews (
    private val newsRepository: NewsRepository
){

    operator fun invoke(sources:List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources= sources)
    }

}