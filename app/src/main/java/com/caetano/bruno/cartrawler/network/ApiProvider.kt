package com.caetano.bruno.cartrawler.network

import com.caetano.bruno.cartrawler.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiProvider {

    fun getApi(baseUrl: String): CartrawlerApi {
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(CartrawlerApi::class.java)
    }

    private fun getClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            addStetho(builder)
            addLogging(builder)
        }

        return builder.build()
    }

    private fun addStetho(builder: OkHttpClient.Builder) {
        builder.addNetworkInterceptor(StethoInterceptor())
    }

    private fun addLogging(builder: OkHttpClient.Builder) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
    }
}