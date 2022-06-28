package com.example.retrofit.network

import com.example.retrofit.dto.SearchNewsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/everything")
    fun getSearchNews(
        @Query("q") search: String? = null,
        @Query("apiKey") apiKey: String? = null
    ) : Call<SearchNewsDto>
}