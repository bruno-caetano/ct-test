package com.caetano.bruno.data.model

import com.google.gson.annotations.SerializedName

data class VehicleEntity(
    @SerializedName("@AirConditionInd")
    val airConditionInd: String,
    @SerializedName("@BaggageQuantity")
    val baggageQuantity: String,
    @SerializedName("@Code")
    val code: String,
    @SerializedName("@CodeContext")
    val codeContext: String,
    @SerializedName("@DoorCount")
    val doorCount: String,
    @SerializedName("@DriveType")
    val driveType: String,
    @SerializedName("@FuelType")
    val fuelType: String,
    @SerializedName("@PassengerQuantity")
    val passengerQuantity: String,
    @SerializedName("@TransmissionType")
    val transmissionType: String,
    @SerializedName("PictureURL")
    val pictureURL: String,
    @SerializedName("VehMakeModel")
    val vehicleMakeModel: VehicleMakeModelEntity
)