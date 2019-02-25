package com.caetano.bruno.data.model

import com.google.gson.annotations.SerializedName

data class VendorEntity(
    @SerializedName("@Code")
    val code: String,
    @SerializedName("@Name")
    val name: String
)