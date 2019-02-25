package com.caetano.bruno.cartrawler.framework

import com.caetano.bruno.cartrawler.network.CartrawlerApi
import com.caetano.bruno.data.model.ServerResponse
import com.caetano.bruno.data.source.VehicleService

class NetworkVehicleService(private val api: CartrawlerApi) : VehicleService {
    override fun loadVehicles(): ServerResponse {
        val response = api.getRentalResult().execute()

        if (response.isSuccessful && response.body() != null)
            return response.body()!!.first()

        throw Exception("Could not download rental data")
    }
}