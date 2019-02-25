package com.caetano.bruno.cartrawler.utils

import com.caetano.bruno.cartrawler.network.ApiProvider
import com.caetano.bruno.cartrawler.network.CartrawlerApi
import okhttp3.mockwebserver.MockWebServer

object NetworkTestUtils {
    fun getApi(mockWebServer: MockWebServer): CartrawlerApi {
        return ApiProvider().getApi(mockWebServer.url("/").toString())
    }
}