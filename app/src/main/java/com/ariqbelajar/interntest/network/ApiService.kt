package com.ariqbelajar.interntest.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    suspend fun getUser(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ) : Users
}