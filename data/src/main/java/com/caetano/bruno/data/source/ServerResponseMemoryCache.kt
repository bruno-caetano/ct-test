package com.caetano.bruno.data.source

import com.caetano.bruno.data.model.ServerResponse
import com.caetano.bruno.data.model.VehicleAvailableEntity

class ServerResponseMemoryCache : ServerResponseCache {

    private var serverResponse: ServerResponse? = null

    override fun isDirty() = serverResponse == null

    override fun save(serverResponse: ServerResponse) {
        this.serverResponse = serverResponse
    }

    override fun get(): ServerResponse {
        return this.serverResponse ?: throw Exception("Cache is empty")
    }

    override fun get(vendorCode: String, vehicleCode: String): VehicleAvailableEntity {

        val vendor = (serverResponse ?: throw Exception("Cache is empty"))
            .vehiclesAvailableRentalCoreEntity
            .availableVendors
            .asSequence()
            .filter { it.vendor.code == vendorCode }.first()

        return vendor.vehiclesAvailable
            .asSequence()
            .filter { it.vehicle.code == vehicleCode }
            .first()
    }
}