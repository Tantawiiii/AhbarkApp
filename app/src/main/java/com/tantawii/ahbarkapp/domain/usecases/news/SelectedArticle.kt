package com.tantawii.ahbarkapp.domain.usecases.news

import com.tantawii.ahbarkapp.data.local.NewsDao
import com.tantawii.ahbarkapp.domain.model.Article
import com.tantawii.ahbarkapp.domain.repository.NewsRepository

class SelectedArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)

    }

}