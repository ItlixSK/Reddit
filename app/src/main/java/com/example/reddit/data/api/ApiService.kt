package com.example.reddit.data.api

import com.example.reddit.model.ModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top.json")
    suspend fun getTop(
        @Query
        ("limit") loadSize: Int = 0,
        @Query
        ("after") after: String? = null,
        @Query
        ("before") before: String? = null
    ): Response<ModelResponse>
}