package com.example.newsapp.api

import com.route.newsappc39_g_sat.model.ArticlesResponse
import com.route.newsappc39_g_sat.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("category") categoryId: String?
        //@Query("country") countryLang : String
    ): Call<SourcesResponse>

    @GET("everything")
    fun getNewsArticles(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String
        //@Query("country") countryLang : String
    ): Call<ArticlesResponse>
}