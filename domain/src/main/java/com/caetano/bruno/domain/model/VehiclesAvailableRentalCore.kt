package com.caetano.bruno.domain.model

data class VehiclesAvailableRentalCore(
    val rentalInfo: RentalInfo,
    val availableVendors: List<AvailableVendor>
)