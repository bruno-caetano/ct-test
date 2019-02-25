package com.caetano.bruno.domain.model

data class Vehicle(
    val airConditionInd: String,
    val baggageQuantity: String,
    val code: String,
    val codeContext: String,
    val doorCount: String,
    val driveType: String,
    val fuelType: String,
    val passengerQuantity: String,
    val transmissionType: String,
    val pictureURL: String,
    val vehicleMakeModel: VehicleMakeModel
)