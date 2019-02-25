package com.caetano.bruno.cartrawler.presentation.detail

import android.support.annotation.VisibleForTesting
import com.caetano.bruno.cartrawler.presentation.converter.RentalConverter
import com.caetano.bruno.domain.usecase.LoadVehicleUseCase

class RentalDetailsPresenter(
    private val loadVehicleUseCase: LoadVehicleUseCase,
    private val rentalConverter: RentalConverter
) :
    RentalDetailsContract.Presenter {

    @VisibleForTesting
    var view: RentalDetailsContract.View? = null

    override fun init(view: RentalDetailsContract.View) {
        this.view = view
    }

    override fun loadRental(vendorCode: String, vehicleCode: String) {
        loadVehicleUseCase.execute(
            LoadVehicleUseCase.VehicleInfoWrapper(vendorCode, vehicleCode),
            {
                view?.showRental(rentalConverter.fromVehicleToDetailViewModel(it))
            }, {
                view?.showError()
            }
        )
    }

    override fun destroy() {
        this.view = null
    }
}