package com.newsApp.view.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import com.newsApp.repositories.ApiServiceRepositoryEdit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "EditViewModel"


class SavedViewModel :ViewModel() {
   private val apiRepo = ApiServiceRepositoryEdit.get()

   val myNewsLiveData = MutableLiveData<List<Article>>()
    val editLiveData = MutableLiveData<String>()
    val deleteLiveData = MutableLiveData<String>()
    val saveLiveData = MutableLiveData<String>()
    val saveErrorLiveData = MutableLiveData<String>()

    fun callMyNews() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.getMyNews()
                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        myNewsLiveData.postValue(this)
                    }

                } else {
                    Log.d(TAG, response.message())

                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }


        }
    }
        fun editMyNews(MyNewsBody: Article) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = apiRepo.editMyNews(MyNewsBody.id, MyNewsBody)
//                 هنا
                    if (response.isSuccessful) {
                        response.body()?.run {
                            Log.d(TAG, this.toString())
                            editLiveData.postValue("successful")
                        }
                    }
                } catch (e: Exception) {
                    Log.d(TAG, e.message.toString())
                }
            }
        }
            fun deleteMyNews(Article: Article) {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = apiRepo.deleteMyNews(Article.id)
                    if (response.isSuccessful) {
                        Log.d(TAG, this.toString())

                    }

                }
            }

    fun addMyNewsLiveData(MyNewsBody: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.addMyNews(MyNewsBody)
                if (!response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                       saveLiveData.postValue("success")

                    }
                } else {
                    Log.d(TAG,"Not Succes ${response.message()}")
                    saveErrorLiveData.postValue(response.message())

                }
            } catch (e: java.lang.Exception) {
                Log.d(TAG, e.message.toString())
                saveErrorLiveData.postValue(e.message.toString())
            }
        }
    }

        }









