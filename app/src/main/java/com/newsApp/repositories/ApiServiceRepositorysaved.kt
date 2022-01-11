package com.newsApp.repositories

import android.content.Context
import com.newsApp.api.NewssavedApi
import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

private const val BASE_URL ="https://61af59a53e2aba0017c4920a.mockapi.io/"
class ApiServiceRepositoryEdit(val context: Context) {


    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitApi =retrofitService. create(NewssavedApi::class.java)

    suspend fun getMyNews() = retrofitApi.getMyNews()

    suspend fun addMyNews(MyNewsBody:Article) = retrofitApi.addMyNews(MyNewsBody)

    suspend fun editMyNews(id:String,MyNewsBody: Article) = retrofitApi.editMyNews(id,MyNewsBody)

    suspend fun  deleteMyNews(id: String) = retrofitApi.deleteMyNews(id)




    companion object{


        private var instance: ApiServiceRepositoryEdit? = null
        //this function will help initialize the repository

        fun init(context: Context) {
            if(instance==null)
                instance = ApiServiceRepositoryEdit(context)
        }

        //this function will help get the api service
        fun get(): ApiServiceRepositoryEdit {
            return instance ?: throw Exception("ApiServiceRepository must be initialized")
        }
    }



}



