package com.caetano.bruno.data.model

import com.google.gson.annotations.SerializedName

data class TotalChargeEntity(
    @SerializedName("@CurrencyCode")
    val currencyCode: String,
    @SerializedName("@EstimatedTotalAmount")
    val estimatedTotalAmount: String,
    @SerializedName("@RateTotalAmount")
    val rateTotalAmount: String
)