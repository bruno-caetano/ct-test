package com.caetano.bruno.domain.usecase

import com.caetano.bruno.domain.model.VehicleAvailable
import com.caetano.bruno.domain.repository.VehicleRepository

class LoadVehicleUseCase(private val repository: VehicleRepository) :
    UseCase<LoadVehicleUseCase.VehicleInfoWrapper, VehicleAvailable>() {

    override fun run(params: VehicleInfoWrapper): VehicleAvailable {
        return repository.loadVehicleInformation(params.vendorCode, params.vehicleCode)
    }

    data class VehicleInfoWrapper(val vendorCode: String, val vehicleCode: String)

}

