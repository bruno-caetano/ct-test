package com.caetano.bruno.cartrawler.utils

import com.caetano.bruno.data.converter.VehiclesAvailableRentalCoreConverter
import com.caetano.bruno.data.model.ServerResponse
import com.caetano.bruno.domain.model.VehiclesAvailableRentalCore
import com.google.gson.Gson

object RentalCoreFactory {
    fun getRentalCore(): VehiclesAvailableRentalCore {
        val serverResponse = Gson().fromJson(
            JsonTestUtils.getJson("json/rental_info.json"),
            Array<ServerResponse>::class.java
        )
        return VehiclesAvailableRentalCoreConverter().convertServerResponseToRentalCore(serverResponse.first())
    }
}