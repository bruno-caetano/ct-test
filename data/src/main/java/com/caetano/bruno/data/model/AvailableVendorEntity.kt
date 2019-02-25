package com.caetano.bruno.data.model

import com.google.gson.annotations.SerializedName

data class AvailableVendorEntity(
    @SerializedName("VehAvails")
    val vehiclesAvailable: List<VehicleAvailableEntity>,
    @SerializedName("Vendor")
    val vendor: VendorEntity
)