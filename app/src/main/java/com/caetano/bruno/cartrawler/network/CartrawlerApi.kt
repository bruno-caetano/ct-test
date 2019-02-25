package com.caetano.bruno.cartrawler.network

import com.caetano.bruno.data.model.ServerResponse
import retrofit2.Call
import retrofit2.http.GET

interface CartrawlerApi {
    @GET("cars.json")
    fun getRentalResult(): Call<Array<ServerResponse>>
}