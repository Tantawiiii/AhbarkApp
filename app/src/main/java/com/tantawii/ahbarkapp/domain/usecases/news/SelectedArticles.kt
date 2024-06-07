package com.tantawii.ahbarkapp.domain.usecases.news

import com.tantawii.ahbarkapp.data.local.NewsDao
import com.tantawii.ahbarkapp.domain.model.Article
import com.tantawii.ahbarkapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectedArticles (
    private val  newsRepository: NewsRepository
) {

     operator fun invoke(): Flow<List<Article>>{
        return newsRepository.selectArticles()

    }
}