package com.newsApp.repositories

import android.content.Context
import com.newsApp.api.NewsApi
import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


private const val BASE_URL = "https://newsapi.org"
class ApiServiceRepository(val context: Context) {


    private val retrofitService = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()


    private val retrofitApi = retrofitService.create(NewsApi::class.java)


  suspend fun getNews() = retrofitApi.getNews()



    companion object{


        private var instance: ApiServiceRepository? = null
        //this function will help initialize the repository

        fun init(context: Context) {
            if(instance==null)
                instance = ApiServiceRepository(context)
        }

        //this function will help get the api service
        fun get(): ApiServiceRepository{
            return instance ?: throw Exception("ApiServiceRepository must be initialized")
        }
    }
}