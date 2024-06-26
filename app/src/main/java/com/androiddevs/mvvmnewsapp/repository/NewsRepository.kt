package com.androiddevs.mvvmnewsapp.repository

import androidx.lifecycle.LiveData
import com.androiddevs.mvvmnewsapp.api.RetrofitInstance
import com.androiddevs.mvvmnewsapp.db.ArticleDao
import com.androiddevs.mvvmnewsapp.models.Article

class NewsRepository (val newsArticleDao: ArticleDao){
    val allArticla: LiveData<List<Article>> = newsArticleDao.getAllArticles()

    suspend fun upsertArticle(article: Article){
        newsArticleDao.upsertArticle(article)
    }
    suspend fun deleteNote(article: Article){
        newsArticleDao.deleteArticle(article)
    }

    // data from api
    suspend fun getBreakingNews(countryCode: String,pageNumber:Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

}