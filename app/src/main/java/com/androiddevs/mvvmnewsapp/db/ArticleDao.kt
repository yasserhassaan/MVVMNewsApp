package com.androiddevs.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.mvvmnewsapp.models.Article

@Dao
interface ArticleDao {

    @Query("select*From articles ")
    fun getAllArticles():LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // insert and update
    suspend fun upsertArticle(article: Article)
    @Delete
    suspend fun deleteArticle(article: Article)
}