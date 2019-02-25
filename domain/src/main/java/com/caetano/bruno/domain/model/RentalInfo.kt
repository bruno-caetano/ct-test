package com.caetano.bruno.domain.model

import java.util.Date

data class RentalInfo(
    val pickUpDateTime: Date,
    val returnDateTime: Date,
    val pickUpLocation: PickUpLocation,
    val returnLocation: ReturnLocation
)