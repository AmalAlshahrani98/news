package com.newsApp.api


import com.newsApp.model.Article
import com.newsApp.model.SaveNews
import com.newsApp.model.news
import com.squareup.okhttp.ResponseBody

import retrofit2.Response
import retrofit2.http.*

interface NewsApi {


    @GET("/v2/top-headlines?sources=bbc-news&apiKey=35b42108cb1a466a9c1299856fec2e6a")
    suspend fun getNews(): Response<news>



}