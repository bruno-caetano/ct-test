package com.caetano.bruno.data.model

import com.google.gson.annotations.SerializedName

data class VehiclesAvailableRentalCoreEntity(
    @SerializedName("VehRentalCore")
    val rentalInfo: RentalInfoEntity,
    @SerializedName("VehVendorAvails")
    val availableVendors: List<AvailableVendorEntity>
)