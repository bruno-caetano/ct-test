package com.caetano.bruno.data.source

import com.caetano.bruno.data.model.AvailableVendorEntity
import com.caetano.bruno.data.model.ServerResponse
import com.caetano.bruno.data.model.VehicleAvailableEntity
import com.caetano.bruno.data.model.VehicleEntity
import com.caetano.bruno.data.model.VehiclesAvailableRentalCoreEntity
import com.caetano.bruno.data.model.VendorEntity
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class ServerResponseMemoryCacheTest {

    private lateinit var cache: ServerResponseCache

    @Before
    fun setUp() {
        cache = ServerResponseMemoryCache()
    }

    @Test
    fun `Cache starts dirty`() {
        Assert.assertTrue(cache.isDirty())
    }

    @Test
    fun `Cache is not dirty once we save something`() {
        cache.save(mock())
        Assert.assertFalse(cache.isDirty())
    }

    @Test
    fun `Cache returns saved data`() {
        val serverResponse: ServerResponse = mock()
        cache.save(serverResponse)
        Assert.assertEquals(serverResponse, cache.get())
    }

    @Test
    fun `Get specific vehicle info from cache`() {
        cache.save(getVehicleInfo())
        Assert.assertNotNull(cache.get("vendor", "vehicle"))

    }

    private fun getVehicleInfo(): ServerResponse {
        return ServerResponse(
            VehiclesAvailableRentalCoreEntity(
                mock(), listOf(
                    AvailableVendorEntity(
                        listOf(
                            VehicleAvailableEntity(
                                "",
                                mock(),
                                VehicleEntity(
                                    "",
                                    "",
                                    "vehicle",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    mock()
                                )
                            )
                        ),
                        VendorEntity("vendor", "")
                    )
                )
            )
        )
    }
}