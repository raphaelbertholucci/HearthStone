package com.bertholucci.home.di

import com.bertholucci.home.ui.card.CardViewModel
import com.bertholucci.home.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(getCardsUseCase = get()) }
    viewModel { (cardName: String) -> CardViewModel(cardName = cardName, getCardByName = get()) }
}
