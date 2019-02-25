package com.caetano.bruno.domain.usecase

import com.caetano.bruno.domain.model.VehiclesAvailableRentalCore
import com.caetano.bruno.domain.repository.VehicleRepository
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoadRentalsUseCaseTest {

    @Mock
    private lateinit var vehicleRepository: VehicleRepository

    private lateinit var loadRentalsUseCase: LoadRentalsUseCase

    @Before
    fun setUp() {
        loadRentalsUseCase = LoadRentalsUseCase(vehicleRepository)
    }

    @Test
    fun `Load vehicles from repository`() {
        loadRentalsUseCase.run(UseCase.None())
        verify(vehicleRepository).loadVehicles()
    }

    @Test
    fun `Successfully loading rental core`() {
        val rentalCore: VehiclesAvailableRentalCore = mock()
        `when`(vehicleRepository.loadVehicles()).thenReturn(rentalCore)
        val result = loadRentalsUseCase.run(UseCase.None())
        Assert.assertEquals(rentalCore, result)
    }

    @Test(expected = Exception::class)
    fun `Error loading rental core`() {
        doThrow(Exception()).`when`(vehicleRepository).loadVehicles()
        loadRentalsUseCase.run(UseCase.None())
    }
}