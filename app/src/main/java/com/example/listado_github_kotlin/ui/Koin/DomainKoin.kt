package com.example.listado_github_kotlin.ui.Koin

import com.example.listado_github_kotlin.ui.NetworkController
import com.example.listado_github_kotlin.ui.main.webservice.MyClient
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val domainKoin = module {

    single {

        provideRetrofit()

    }

    single {
        NetworkController()
    }

}

private fun provideRetrofit(): MyClient = Retrofit.Builder()
    .baseUrl(MyClient.BASE_URL)
    .client(OkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()
    .create(MyClient::class.java)