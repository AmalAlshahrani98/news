package com.newsApp.view.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import com.newsApp.repositories.ApiServiceRepositoryEdit
import com.newsApp.view.NewsViewModel
import io.grpc.InternalChannelz.id
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "EditViewModel"


class SavedViewModel :ViewModel() {
   private val apiRepo = ApiServiceRepositoryEdit.get()

   val myNewsLiveData = MutableLiveData<List<SaveNews>>()
    val editLiveData = MutableLiveData<String>()
    val deleteLiveData = MutableLiveData<String>()
    val saveLiveData = MutableLiveData<List<SaveNews>>()
    val saveErrorLiveData = MutableLiveData<List<SaveNews>>()

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
        fun editMyNews(MyNewsBody: SaveNews) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = apiRepo.editMyNews(MyNewsBody.userid, MyNewsBody)
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
            fun deleteMyNews(saveNews: SaveNews) {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = apiRepo.deleteMyNews(saveNews.id)
                    if (response.isSuccessful) {
                        response.body()?.run {
                            Log.d(TAG, this.toString())
                            deleteLiveData.postValue("successful")
                        }
                    }

                }
            }

    fun addMyNewsLiveData(MyNewsBody: SaveNews) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.addMyNews(MyNewsBody)
                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                      //  saveLiveData.postValue("success")

                    }
                } else {
                    Log.d(TAG,"Not Succes ${response.message()}")
//                    saveErrorLiveData.postValue(response.message())

                }
            } catch (e: java.lang.Exception) {
                Log.d(TAG, e.message.toString())
              //  saveErrorLiveData.postValue(e.message.toString())
            }
        }
    }

        }











