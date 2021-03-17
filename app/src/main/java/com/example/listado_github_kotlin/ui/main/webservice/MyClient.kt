package com.example.listado_github_kotlin.ui.main.webservice

import com.example.data.Repository
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface MyClient {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("repositories")
    suspend fun getRepositoryList(): Response<List<Repository>>

    @GET("repositories/{id}")
    suspend fun getRepository(@Path("id") id: String): Response<Repository>

}