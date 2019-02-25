package com.caetano.bruno.cartrawler.presentation.list

import com.caetano.bruno.cartrawler.presentation.model.RentalInfoViewModel
import com.caetano.bruno.cartrawler.presentation.model.RentalItemViewModel
import com.caetano.bruno.cartrawler.presentation.model.SortType

interface RentalListContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun showRentalInfo(rentalInfoViewModel: RentalInfoViewModel)
        fun showRentals(rentals: List<RentalItemViewModel>)
    }

    interface Presenter {
        fun init(view: View)
        fun destroy()
        fun loadRentals()
        fun sortBy(sortType: SortType)
    }
}