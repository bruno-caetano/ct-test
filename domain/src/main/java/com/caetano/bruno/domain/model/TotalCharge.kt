package com.caetano.bruno.domain.model

import java.math.BigDecimal

data class TotalCharge(
    val currencyCode: String,
    val estimatedTotalAmount: BigDecimal,
    val rateTotalAmount: BigDecimal
)