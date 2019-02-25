package com.caetano.bruno.domain.model

data class AvailableVendor(
    val vehiclesAvailable: List<VehicleAvailable>,
    val vendor: Vendor
)