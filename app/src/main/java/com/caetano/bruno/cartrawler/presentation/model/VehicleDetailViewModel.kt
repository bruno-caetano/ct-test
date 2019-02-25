package com.caetano.bruno.cartrawler.presentation.model

class VehicleDetailViewModel(
    makeModel: String,
    pictureUrl: String,
    passengerQuantity: String,
    doorQuantity: String,
    baggageQuantity: String,
    totalPrice: String,
    currency: String,
    val airConditioner: Boolean,
    val fuelType: String,
    val transmission: String
) : VehicleViewModel(
    makeModel,
    pictureUrl,
    passengerQuantity,
    doorQuantity,
    baggageQuantity,
    totalPrice,
    currency
)