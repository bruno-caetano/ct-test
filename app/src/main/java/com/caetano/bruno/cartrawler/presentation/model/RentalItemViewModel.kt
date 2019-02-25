package com.caetano.bruno.cartrawler.presentation.model

class RentalItemViewModel(
    makeModel: String,
    pictureUrl: String,
    passengerQuantity: String,
    doorQuantity: String,
    baggageQuantity: String,
    totalPrice: String,
    currency: String,
    val vendorCode: String,
    val vehicleCode: String,
    val vendor: String
) : VehicleViewModel(
    makeModel,
    pictureUrl,
    passengerQuantity,
    doorQuantity,
    baggageQuantity,
    totalPrice,
    currency
)

enum class SortType {
    PRICE_ASC, PRICE_DESC, VENDOR_AZ, VENDOR_ZA
}