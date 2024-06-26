package com.tantawii.ahbarkapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tantawii.ahbarkapp.domain.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertArticle(article: Article)


    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM article")
    fun getAllArticles(): Flow<List<Article>>


    @Query("SELECT * FROM article WHERE url =:url")
    suspend fun getArticleByUrl(url: String): Article?


}