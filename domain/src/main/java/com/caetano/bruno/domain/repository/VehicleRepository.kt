package com.caetano.bruno.domain.repository

import com.caetano.bruno.domain.model.VehicleAvailable
import com.caetano.bruno.domain.model.VehiclesAvailableRentalCore

interface VehicleRepository {
    /* If this was a search we could receive the search query here */
    fun loadVehicles(): VehiclesAvailableRentalCore

    fun loadVehicleInformation(vendorCode: String, vehicleCode: String): VehicleAvailable
}