package com.newsApp.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsApp.model.Article
import com.newsApp.model.news
import com.newsApp.repositories.ApiServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "NewsViewModel"
class NewsViewModel: ViewModel() {

    private val apiRepository = ApiServiceRepository.get()


    val newsLiveData= MutableLiveData<List<Article>>()
    val newsErrorLiveData = MutableLiveData<String>()




    fun callNews(){
       viewModelScope.launch(Dispatchers.IO){
           try{
               val response = apiRepository.getNews()
               if(response.isSuccessful){


                   response.body()?.run {
                       Log.d(TAG,this.toString())
                       newsLiveData.postValue(articles)
                   }
               } else{
                   Log.d(TAG, response.message())
                   newsErrorLiveData.postValue(response.message())
               }
           } catch (e: Exception){
               Log.d(TAG, e.message.toString())
               newsErrorLiveData.postValue(e.message.toString())
           }
       }

    }


}