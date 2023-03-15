package com.example.ltech.data.api

import com.example.ltech.data.models.remote.articles.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArticlesResponseApi {

    @GET("/api/v1/posts")
    suspend fun getArticles(): Response<ArticlesResponse>
}