package com.caetano.bruno.cartrawler.presentation.detail

import com.caetano.bruno.cartrawler.presentation.converter.RentalConverter
import com.caetano.bruno.cartrawler.utils.RentalCoreFactory
import com.caetano.bruno.domain.model.VehicleAvailable
import com.caetano.bruno.domain.usecase.LoadVehicleUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RentalDetailsPresenterTest {

    @Mock
    lateinit var loadVehicleUseCase: LoadVehicleUseCase

    @Mock
    lateinit var view: RentalDetailsContract.View

    private lateinit var presenter: RentalDetailsContract.Presenter

    @Before
    fun setUp() {
        presenter = RentalDetailsPresenter(loadVehicleUseCase,
            RentalConverter()
        )
        presenter.init(view)
    }

    @Test
    fun `Init view`() {
        Assert.assertNotNull((presenter as RentalDetailsPresenter).view)
    }

    @Test
    fun `Destroy view`() {
        presenter.destroy()
        Assert.assertNull((presenter as RentalDetailsPresenter).view)
    }

    @Test
    fun `Load vehicle information`() {
        presenter.loadRental("vendorCode", "vehicleCode")
        verify(loadVehicleUseCase).execute(
            eq(
                LoadVehicleUseCase.VehicleInfoWrapper(
                    "vendorCode",
                    "vehicleCode"
                )
            ), any(), any()
        )
    }

    @Test
    fun `Present vehicle data`() {
        val captor = argumentCaptor<(VehicleAvailable) -> Unit>()
        presenter.loadRental("vendorCode", "vehicleCode")
        verify(loadVehicleUseCase).execute(any(), captor.capture(), any())

        val rentalCore = RentalCoreFactory.getRentalCore()
        captor.firstValue.invoke(rentalCore.availableVendors.first().vehiclesAvailable.first())

        verify(view).showRental(any())
    }

    @Test
    fun `Error loading vehicle data`() {
        val captor = argumentCaptor<(Exception) -> Unit>()
        presenter.loadRental("vendorCode", "vehicleCode")
        verify(loadVehicleUseCase).execute(any(), any(), captor.capture())
        captor.firstValue.invoke(Exception())

        verify(view).showError()
    }

}