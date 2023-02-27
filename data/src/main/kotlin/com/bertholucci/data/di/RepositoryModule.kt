package com.bertholucci.data.di

import com.bertholucci.data.repository.CardsRepositoryImpl
import com.bertholucci.domain.repository.CardsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<CardsRepository> { CardsRepositoryImpl(api = get()) }
}
