package com.caetano.bruno.data.model

import com.google.gson.annotations.SerializedName

data class VehicleAvailableEntity(
    @SerializedName("@Status")
    val status: String,
    @SerializedName("TotalCharge")
    val totalCharge: TotalChargeEntity,
    @SerializedName("Vehicle")
    val vehicle: VehicleEntity
)