package com.caetano.bruno.cartrawler.framework

import com.caetano.bruno.cartrawler.utils.JsonTestUtils
import com.caetano.bruno.cartrawler.utils.NetworkTestUtils
import com.caetano.bruno.data.model.ServerResponse
import com.caetano.bruno.data.source.VehicleService
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class NetworkVehicleServiceTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var service: VehicleService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        service = NetworkVehicleService(NetworkTestUtils.getApi(mockWebServer))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Receive network data`() {
        val rentalInfo = JsonTestUtils.getJson("json/rental_info.json")

        val mockResponse = MockResponse()
        mockResponse.setBody(rentalInfo)
        mockResponse.setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val response = service.loadVehicles()

        val responseSent = Gson().fromJson(rentalInfo, Array<ServerResponse>::class.java)

        Assert.assertEquals(responseSent.first(), response)
    }

    @Test(expected = Exception::class)
    fun `Fail to download network data`() {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(500)

        mockWebServer.enqueue(mockResponse)

        service.loadVehicles()
    }

}