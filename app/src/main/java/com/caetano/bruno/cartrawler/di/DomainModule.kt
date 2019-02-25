package com.caetano.bruno.cartrawler.di

import com.caetano.bruno.domain.usecase.LoadRentalsUseCase
import com.caetano.bruno.domain.usecase.LoadVehicleUseCase
import org.koin.dsl.module.module

val domainModule = module {
    factory { LoadRentalsUseCase(get()) }
    factory { LoadVehicleUseCase(get()) }
}