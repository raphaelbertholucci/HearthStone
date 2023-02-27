package com.bertholucci.domain.di

import com.bertholucci.domain.interactor.GetCardByName
import com.bertholucci.domain.interactor.GetCards
import org.koin.dsl.module

val domainModule = module {
    factory { GetCards(repository = get()) }
    factory { GetCardByName(repository = get()) }
}