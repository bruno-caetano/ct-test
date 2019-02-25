package com.caetano.bruno.cartrawler.di

import com.caetano.bruno.data.converter.VehiclesAvailableRentalCoreConverter
import com.caetano.bruno.data.repository.VehicleDataRepository
import com.caetano.bruno.data.source.ServerResponseMemoryCache
import com.caetano.bruno.data.source.ServerResponseCache
import com.caetano.bruno.domain.repository.VehicleRepository
import org.koin.dsl.module.module

val dataModule = module {
    factory { VehiclesAvailableRentalCoreConverter() }
    single<ServerResponseCache> { ServerResponseMemoryCache() }
    factory<VehicleRepository> { VehicleDataRepository(get(), get(), get()) }
}