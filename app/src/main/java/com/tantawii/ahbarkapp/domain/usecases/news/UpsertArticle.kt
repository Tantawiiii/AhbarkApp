package com.tantawii.ahbarkapp.domain.usecases.news

import com.tantawii.ahbarkapp.data.local.NewsDao
import com.tantawii.ahbarkapp.domain.model.Article
import com.tantawii.ahbarkapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)

    }

}