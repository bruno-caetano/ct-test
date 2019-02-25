package com.caetano.bruno.data.model

import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @SerializedName("VehAvailRSCore")
    val vehiclesAvailableRentalCoreEntity: VehiclesAvailableRentalCoreEntity
)