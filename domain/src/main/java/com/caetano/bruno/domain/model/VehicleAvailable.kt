package com.caetano.bruno.domain.model

data class VehicleAvailable(
    val status: String,
    val totalCharge: TotalCharge,
    val vehicle: Vehicle
)