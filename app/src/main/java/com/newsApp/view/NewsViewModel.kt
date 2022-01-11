package com.newsApp.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import com.newsApp.model.news
import com.newsApp.repositories.ApiServiceRepository
import com.newsApp.repositories.ApiServiceRepositoryEdit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "NewsViewModel"
class NewsViewModel: ViewModel() {

    private val apiRepository = ApiServiceRepository.get()
    private val apiRepoSaved = ApiServiceRepositoryEdit.get()
    val addLiveData = MutableLiveData<String>()
    val deleteErrorLiveData = MutableLiveData<String>()
    val newsLiveData = MutableLiveData<List<Article>>()
    val newsErrorLiveData = MutableLiveData<String>()
   // val addMyNewsLiveData = MutableLiveData<String>()
    val addMyNewsErrorLiveData =MutableLiveData<String>()




    var item: NewsViewModel? = null
    var selectedItemMutableLiveDate = MutableLiveData<NewsViewModel>()


    fun callNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepository.getNews()
                if (response.isSuccessful) {


                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        newsLiveData.postValue(articles)
                    }
                } else {
                    Log.d(TAG, response.message())
                    newsErrorLiveData.postValue(response.message())
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                newsErrorLiveData.postValue(e.message.toString())
            }
        }

    }

    fun addMyNewsLiveData(MyNewsBody: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepoSaved.addMyNews(MyNewsBody)
                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        addLiveData.postValue("success")
                        Log.d(TAG, "response.success ${response.message()}")
                    }
                } else {
                    Log.d(TAG, response.message())
                    addMyNewsErrorLiveData.postValue(response.message())

                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                addMyNewsErrorLiveData.postValue(e.message.toString())
            }
        }
    }



}




