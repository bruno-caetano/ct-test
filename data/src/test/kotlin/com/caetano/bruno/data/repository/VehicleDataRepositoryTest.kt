package com.caetano.bruno.data.repository

import com.caetano.bruno.data.converter.VehiclesAvailableRentalCoreConverter
import com.caetano.bruno.data.model.ServerResponse
import com.caetano.bruno.data.model.VehicleAvailableEntity
import com.caetano.bruno.data.source.ServerResponseMemoryCache
import com.caetano.bruno.data.source.VehicleService
import com.caetano.bruno.domain.repository.VehicleRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VehicleDataRepositoryTest {

    @Mock
    private lateinit var vehicleService: VehicleService

    @Mock
    private lateinit var memoryCache: ServerResponseMemoryCache

    @Mock
    private lateinit var converter: VehiclesAvailableRentalCoreConverter

    private lateinit var repository: VehicleRepository

    @Before
    fun setUp() {
        repository = VehicleDataRepository(vehicleService, memoryCache, converter)
    }

    @Test
    fun `When cache is dirty reaches the network to download new data`() {
        doReturn(true).`when`(memoryCache).isDirty()
        repository.loadVehicles()
        verify(vehicleService).loadVehicles()
    }

    @Test
    fun `Saves newly downloaded data into cache`() {
        doReturn(true).`when`(memoryCache).isDirty()
        val serverData: ServerResponse = mock()
        doReturn(serverData).`when`(vehicleService).loadVehicles()
        repository.loadVehicles()
        verify(memoryCache).save(serverData)
    }

    @Test
    fun `Use cached data if cache is not dirty`() {
        val cachedData: ServerResponse = mock()
        doReturn(cachedData).`when`(memoryCache).get()
        doReturn(false).`when`(memoryCache).isDirty()
        repository.loadVehicles()
        verify(converter).convertServerResponseToRentalCore(eq(cachedData))
    }

    @Test(expected = Exception::class)
    fun `Throw exception if network fails when downloading data`() {
        doThrow(Exception()).`when`(vehicleService).loadVehicles()
        repository.loadVehicles()
    }

    @Test
    fun `Load vehicle information from cache`() {
        val cachedData: VehicleAvailableEntity = mock()
        doReturn(cachedData).`when`(memoryCache).get(any(), any())
        doReturn(false).`when`(memoryCache).isDirty()

        repository.loadVehicleInformation("", "")

        verify(memoryCache).get(eq(""), eq(""))
        verify(converter).convertVehicleEntityToVehicle(eq(cachedData))
    }

    @Test
    fun `Refresh cache when isDirty`() {
        doReturn(true).`when`(memoryCache).isDirty()
        val serverData: ServerResponse = mock()
        doReturn(serverData).`when`(vehicleService).loadVehicles()
        repository.loadVehicleInformation("", "")
        verify(memoryCache).save(serverData)
    }

    @Test(expected = Exception::class)
    fun `Throw exception if network fails when loading vehicle info`() {
        doThrow(Exception()).`when`(vehicleService).loadVehicles()
        repository.loadVehicleInformation("", "")
    }

}