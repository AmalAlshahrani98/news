package com.newsApp.api

import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import com.newsApp.view.NewsViewModel
import com.squareup.okhttp.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface NewssavedApi {
    @GET("/Article")
    suspend fun getMyNews(

    ):Response<List<SaveNews>>

    @POST("/Article")
    suspend fun addMyNews(
        @Body MyNewsBody:SaveNews
    ):Response<ResponseBody>

    @PUT("/Article{id}")
    suspend fun editMyNews(@Path("id")Id:String,
    @Body MyNewsBody: SaveNews):Response<SaveNews>

    @DELETE("/Article{id}")
    suspend fun deleteMyNews(@Path("id")Id: String):Response<ResponseBody>
}


