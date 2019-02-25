package com.caetano.bruno.cartrawler.presentation.list

import android.support.annotation.VisibleForTesting
import com.caetano.bruno.cartrawler.presentation.converter.RentalConverter
import com.caetano.bruno.cartrawler.presentation.model.RentalItemViewModel
import com.caetano.bruno.cartrawler.presentation.model.SortType
import com.caetano.bruno.domain.model.AvailableVendor
import com.caetano.bruno.domain.model.VehiclesAvailableRentalCore
import com.caetano.bruno.domain.usecase.LoadRentalsUseCase
import com.caetano.bruno.domain.usecase.UseCase

class RentalListPresenter(
    private val loadRentalsUseCase: LoadRentalsUseCase,
    private val converter: RentalConverter
) : RentalListContract.Presenter {

    private var rentalCore: VehiclesAvailableRentalCore? = null

    private var sortStrategy: SortRentalItemStrategy = getStrategy(SortType.PRICE_ASC)

    @VisibleForTesting
    var view: RentalListContract.View? = null

    override fun init(view: RentalListContract.View) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }

    override fun sortBy(sortType: SortType) {
        rentalCore?.let {
            sortStrategy = getStrategy(sortType)
            presentList(getCarList(it))
        }
    }

    private fun getStrategy(sortType: SortType) = when (sortType) {
        SortType.PRICE_ASC -> SortPriceAsc()
        SortType.PRICE_DESC -> SortPriceDesc()
        SortType.VENDOR_AZ -> SortVendorAsc()
        SortType.VENDOR_ZA -> SortVendorDesc()
    }

    override fun loadRentals() {
        view?.showLoading()
        loadRentalsUseCase.execute(UseCase.None(), {
            rentalCore = it
            presentRentalInfo(it)
            presentList(getCarList(it))
            view?.hideLoading()
        }, {
            view?.showError()
        })
    }

    private fun presentRentalInfo(rentalCore: VehiclesAvailableRentalCore) {
        val rentalInfo = converter.fromRentalCoreToRentalInfo(rentalCore)
        view?.showRentalInfo(rentalInfo)
    }

    private fun getCarList(rentalCore: VehiclesAvailableRentalCore): List<RentalItemViewModel> {
        val rentalList = arrayListOf<RentalItemViewModel>()
        for (vendor in rentalCore.availableVendors) {
            rentalList += getExtractVehicles(vendor)
        }
        return rentalList
    }

    private fun getExtractVehicles(vendor: AvailableVendor): ArrayList<RentalItemViewModel> {
        val rentalList = ArrayList<RentalItemViewModel>()
        for (availableVehicle in vendor.vehiclesAvailable) {
            rentalList += converter.fromRentalCoreToRentalViewModel(vendor.vendor, availableVehicle)
        }
        return rentalList
    }

    private fun presentList(rentalList: List<RentalItemViewModel>) {
        view?.showRentals(sortStrategy.sort(rentalList))
    }
}