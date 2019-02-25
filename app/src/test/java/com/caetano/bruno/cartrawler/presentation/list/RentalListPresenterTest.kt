package com.caetano.bruno.cartrawler.presentation.list

import com.caetano.bruno.cartrawler.presentation.converter.RentalConverter
import com.caetano.bruno.cartrawler.presentation.model.RentalItemViewModel
import com.caetano.bruno.cartrawler.presentation.model.SortType
import com.caetano.bruno.cartrawler.utils.RentalCoreFactory
import com.caetano.bruno.domain.model.VehiclesAvailableRentalCore
import com.caetano.bruno.domain.usecase.LoadRentalsUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RentalListPresenterTest {

    private lateinit var presenter: RentalListContract.Presenter

    @Mock
    private lateinit var view: RentalListContract.View

    @Mock
    private lateinit var loadRentalsUseCase: LoadRentalsUseCase

    @Before
    fun setUp() {
        presenter = RentalListPresenter(loadRentalsUseCase,
            RentalConverter()
        )
        presenter.init(view)
    }

    @Test
    fun `View is initialized`() {
        Assert.assertNotNull((presenter as RentalListPresenter).view)
    }

    @Test
    fun `View is destroyed`() {
        presenter.destroy()
        Assert.assertNull((presenter as RentalListPresenter).view)
    }

    @Test
    fun `Load rentals`() {
        loadRentals()
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showRentalInfo(any())
        verify(view).showRentals(any())
    }

    private fun loadRentals() {
        presenter.loadRentals()
        val rentalCaptor = argumentCaptor<(VehiclesAvailableRentalCore) -> Unit>()
        verify(loadRentalsUseCase).execute(any(), rentalCaptor.capture(), any())

        rentalCaptor.firstValue.invoke(RentalCoreFactory.getRentalCore())
    }

    @Test
    fun `Sort by price ASC`() {
        loadRentals()
        reset(view)

        presenter.sortBy(SortType.PRICE_ASC)

        val rentalListCaptor = argumentCaptor<List<RentalItemViewModel>>()
        verify(view).showRentals(rentalListCaptor.capture())
        val list = rentalListCaptor.firstValue
        val sorted = list.sortedBy { it.totalPrice.toBigDecimal() }

        Assert.assertEquals(sorted.map { it.totalPrice }, list.map { it.totalPrice })
    }

    @Test
    fun `Sort by price DESC`() {
        loadRentals()
        reset(view)

        presenter.sortBy(SortType.PRICE_DESC)

        val rentalListCaptor = argumentCaptor<List<RentalItemViewModel>>()
        verify(view, atLeastOnce()).showRentals(rentalListCaptor.capture())
        val list = rentalListCaptor.firstValue
        val sorted = list.sortedByDescending { it.totalPrice.toBigDecimal() }

        Assert.assertEquals(sorted.map { it.totalPrice }, list.map { it.totalPrice })
    }

    @Test
    fun `Sort by vendor ASC`() {
        loadRentals()
        reset(view)

        presenter.sortBy(SortType.VENDOR_AZ)

        val rentalListCaptor = argumentCaptor<List<RentalItemViewModel>>()
        verify(view, atLeastOnce()).showRentals(rentalListCaptor.capture())
        val list = rentalListCaptor.firstValue
        val sorted = list.sortedBy { it.vendor }

        Assert.assertEquals(sorted.map { it.vendor }, list.map { it.vendor })
    }

    @Test
    fun `Sort by vendor DESC`() {
        loadRentals()
        reset(view)

        presenter.sortBy(SortType.VENDOR_ZA)

        val rentalListCaptor = argumentCaptor<List<RentalItemViewModel>>()
        verify(view, atLeastOnce()).showRentals(rentalListCaptor.capture())
        val list = rentalListCaptor.firstValue
        val sorted = list.sortedByDescending { it.vendor }

        Assert.assertEquals(sorted.map { it.vendor }, list.map { it.vendor })
    }

    @Test
    fun `Error while loading rentals`() {
        presenter.loadRentals()
        val rentalCaptor = argumentCaptor<(Exception) -> Unit>()
        verify(loadRentalsUseCase).execute(any(), any(), rentalCaptor.capture())

        rentalCaptor.firstValue.invoke(Exception())

        verify(view).showError()
    }

}