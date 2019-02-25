package com.caetano.bruno.cartrawler.presentation.list

import com.caetano.bruno.cartrawler.presentation.model.RentalItemViewModel

interface SortRentalItemStrategy {
    fun sort(list: List<RentalItemViewModel>): List<RentalItemViewModel>
}

class SortPriceAsc : SortRentalItemStrategy {
    override fun sort(list: List<RentalItemViewModel>): List<RentalItemViewModel> {
        return list.sortedBy { it.totalPrice.toBigDecimal() }
    }
}

class SortPriceDesc : SortRentalItemStrategy {
    override fun sort(list: List<RentalItemViewModel>): List<RentalItemViewModel> {
        return list.sortedByDescending { it.totalPrice.toBigDecimal() }
    }
}

class SortVendorAsc : SortRentalItemStrategy {
    override fun sort(list: List<RentalItemViewModel>): List<RentalItemViewModel> {
        return list.sortedBy { it.vendor }
    }
}

class SortVendorDesc : SortRentalItemStrategy {
    override fun sort(list: List<RentalItemViewModel>): List<RentalItemViewModel> {
        return list.sortedByDescending { it.vendor }
    }
}