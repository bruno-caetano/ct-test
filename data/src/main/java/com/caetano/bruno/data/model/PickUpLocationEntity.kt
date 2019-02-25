package com.caetano.bruno.data.model

import com.google.gson.annotations.SerializedName

data class PickUpLocationEntity(
    @SerializedName("@Name")
    val name: String
)