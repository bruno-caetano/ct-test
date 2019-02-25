package com.caetano.bruno.domain.usecase

import com.caetano.bruno.domain.repository.VehicleRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoadVehicleUseCaseTest {

    @Mock
    private lateinit var repository: VehicleRepository

    private lateinit var useCase: LoadVehicleUseCase

    @Before
    fun setUp() {
        useCase = LoadVehicleUseCase(repository)
    }

    @Test
    fun `Load vehicle info from repository`() {
        useCase.run(LoadVehicleUseCase.VehicleInfoWrapper("1234", "5678"))
        verify(repository).loadVehicleInformation("1234", "5678")
    }

}