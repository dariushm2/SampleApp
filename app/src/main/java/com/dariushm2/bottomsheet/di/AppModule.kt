package com.dariushm2.bottomsheet.di

import com.dariushm2.bottomsheet.navigation.*
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single { DestinationFactory }

    single { FirstFragmentCreator() } bind DestinationCreator::class
    single { SecondFragmentCreator() } bind DestinationCreator::class
    single { ThirdFragmentCreator() } bind DestinationCreator::class
    single { HomeFragmentCreator() } bind DestinationCreator::class
    single { MyBetsFragmentCreator() } bind DestinationCreator::class
    single { LiveFragmentCreator() } bind DestinationCreator::class
}