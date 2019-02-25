package com.caetano.bruno.data.model

import com.google.gson.annotations.SerializedName

data class RentalInfoEntity(
    @SerializedName("@PickUpDateTime")
    val pickUpDateTime: String,
    @SerializedName("@ReturnDateTime")
    val returnDateTime: String,
    @SerializedName("PickUpLocation")
    val pickUpLocation: PickUpLocationEntity,
    @SerializedName("ReturnLocation")
    val returnLocation: ReturnLocationEntity
)