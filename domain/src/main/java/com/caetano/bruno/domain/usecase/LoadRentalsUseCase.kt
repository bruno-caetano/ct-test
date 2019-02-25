package com.caetano.bruno.domain.usecase

import com.caetano.bruno.domain.model.VehiclesAvailableRentalCore
import com.caetano.bruno.domain.repository.VehicleRepository

class LoadRentalsUseCase(private val vehicleRepository: VehicleRepository) :
    UseCase<UseCase.None, VehiclesAvailableRentalCore>() {
    override fun run(params: None): VehiclesAvailableRentalCore {
        return vehicleRepository.loadVehicles()
    }
}