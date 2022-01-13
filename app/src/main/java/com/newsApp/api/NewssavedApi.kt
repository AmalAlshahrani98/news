package com.newsApp.api

import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import com.newsApp.view.NewsViewModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface NewssavedApi {
    @GET("/Article")
    suspend fun getMyNews(

    ):Response<List<Article>>

    @POST("/Article")
    suspend fun addMyNews(
        @Body MyNewsBody:Article
    ):Response<ResponseBody>

    @PUT("/Article/{id}")
    suspend fun editMyNews(@Path("id")Id:String,
    @Body MyNewsBody: Article):Response<Article>

    @DELETE("/Article/{id}")
    suspend fun deleteMyNews(@Path("id")Id: String):Response<ResponseBody>
}


