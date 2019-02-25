package com.caetano.bruno.cartrawler.di

import com.caetano.bruno.cartrawler.framework.NetworkVehicleService
import com.caetano.bruno.cartrawler.network.ApiProvider
import com.caetano.bruno.data.source.VehicleService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import com.caetano.bruno.cartrawler.R

const val BASE_URL = "BASE_URL"

val networkModule = module {
    single(BASE_URL) { androidContext().getString(R.string.baseUrl) }
    single { ApiProvider().getApi(get(BASE_URL)) }
    factory<VehicleService> { NetworkVehicleService(get()) }
}