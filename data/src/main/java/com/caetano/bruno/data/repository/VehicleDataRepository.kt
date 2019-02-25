package com.caetano.bruno.data.repository

import com.caetano.bruno.data.converter.VehiclesAvailableRentalCoreConverter
import com.caetano.bruno.data.source.ServerResponseCache
import com.caetano.bruno.data.source.VehicleService
import com.caetano.bruno.domain.model.VehicleAvailable
import com.caetano.bruno.domain.model.VehiclesAvailableRentalCore
import com.caetano.bruno.domain.repository.VehicleRepository

class VehicleDataRepository(
    private val vehicleService: VehicleService,
    private val memoryCache: ServerResponseCache,
    private val converter: VehiclesAvailableRentalCoreConverter
) : VehicleRepository {

    override fun loadVehicleInformation(vendorCode: String, vehicleCode: String): VehicleAvailable {

        if (memoryCache.isDirty()) {
            downloadDataAndCache()
        }

        val vehicleData = memoryCache.get(vendorCode, vehicleCode)
        return converter.convertVehicleEntityToVehicle(vehicleData)
    }

    override fun loadVehicles(): VehiclesAvailableRentalCore {

        if (memoryCache.isDirty()) {
            downloadDataAndCache()
        }

        val serverResponse = memoryCache.get()
        return converter.convertServerResponseToRentalCore(serverResponse)
    }

    private fun downloadDataAndCache() {
        val serverResponse = vehicleService.loadVehicles()
        memoryCache.save(serverResponse)
    }
}