package com.tantawii.ahbarkapp.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectedArticles: SelectedArticles,
    val selectedArticle: SelectedArticle,
)
