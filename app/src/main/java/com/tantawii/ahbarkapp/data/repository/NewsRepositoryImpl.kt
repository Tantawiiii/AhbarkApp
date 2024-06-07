package com.tantawii.ahbarkapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tantawii.ahbarkapp.data.local.NewsDao
import com.tantawii.ahbarkapp.data.remote.NewsApi
import com.tantawii.ahbarkapp.data.remote.NewsPagingSource
import com.tantawii.ahbarkapp.data.remote.SearchNewsPagingSource
import com.tantawii.ahbarkapp.domain.model.Article
import com.tantawii.ahbarkapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ","),
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, source: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    source = source.joinToString(separator = ","),
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsertArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.deleteArticle(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getAllArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticleByUrl(url)
    }


}