package com.caetano.bruno.data.source

import com.caetano.bruno.data.model.ServerResponse
import com.caetano.bruno.data.model.VehicleAvailableEntity

interface ServerResponseCache {
    fun isDirty(): Boolean
    fun save(serverResponse: ServerResponse)
    fun get(): ServerResponse
    fun get(vendorCode: String, vehicleCode: String): VehicleAvailableEntity
}