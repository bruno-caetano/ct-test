package com.caetano.bruno.cartrawler.presentation.detail

import com.caetano.bruno.cartrawler.presentation.model.VehicleDetailViewModel

interface RentalDetailsContract {
    interface View {
        fun showRental(vehicleViewModel: VehicleDetailViewModel)
        fun showError()
    }

    interface Presenter {
        fun init(view: View)
        fun loadRental(vendorCode: String, vehicleCode: String)
        fun destroy()
    }
}