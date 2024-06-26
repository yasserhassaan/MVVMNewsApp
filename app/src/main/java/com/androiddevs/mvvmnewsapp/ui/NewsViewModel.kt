package com.androiddevs.mvvmnewsapp.ui

import android.app.Application
import androidx.lifecycle.*
import com.androiddevs.mvvmnewsapp.db.ArticleDataBase
import com.androiddevs.mvvmnewsapp.models.Article
import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository)
    :ViewModel() {

    /* var allArticle: LiveData<List<Article>>
    var repository:NewsRepository
    init {
        val dao= ArticleDataBase.getDataBase(application).getArticleDao()
        repository=NewsRepository(dao)
        allArticle=repository.allArticla

    }*/
    lateinit var repository: NewsRepository
   // var breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    // data from api
     var breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
     var breakingNewsPage = 2
    init {
        breakingNews("us")
    }

    fun breakingNews(CountryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(CountryCode, breakingNewsPage)
        breakingNews.postValue(handlingBreackingNewsResponse(response))

    }

    fun handlingBreackingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())

    }
}

/*fun upsert(article: Article)=viewModelScope.launch(Dispatchers.IO) {
    repository.upsertArticle(article)
}

fun deleteArticle(article: Article)=viewModelScope.launch(Dispatchers.IO) {
    repository.deleteNote(article)
}*/
