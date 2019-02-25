package com.caetano.bruno.cartrawler.di

import com.caetano.bruno.cartrawler.presentation.converter.RentalConverter
import com.caetano.bruno.cartrawler.presentation.detail.RentalDetailsContract
import com.caetano.bruno.cartrawler.presentation.detail.RentalDetailsPresenter
import com.caetano.bruno.cartrawler.presentation.list.RentalListContract
import com.caetano.bruno.cartrawler.presentation.list.RentalListPresenter
import org.koin.dsl.module.module

val presentationModule = module {
    factory { RentalConverter() }
    single<RentalListContract.Presenter> { RentalListPresenter(get(), get()) }
    single<RentalDetailsContract.Presenter> { RentalDetailsPresenter(get(), get()) }
}