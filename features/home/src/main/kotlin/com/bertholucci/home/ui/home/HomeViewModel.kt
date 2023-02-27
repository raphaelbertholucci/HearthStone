package com.bertholucci.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.domain.helper.HearthStoneResponse
import com.bertholucci.domain.interactor.GetCards
import com.bertholucci.domain.model.Card
import com.bertholucci.home.extensions.response

class HomeViewModel(private val getCardsUseCase: GetCards) : ViewModel() {

    private val _cards = MutableLiveData<HearthStoneResponse<List<Card>>>()
    val cards: LiveData<HearthStoneResponse<List<Card>>>
        get() = _cards

    init {
        getCards()
    }

    fun getCards() {
        getCardsUseCase(Unit).response(_cards, viewModelScope)
    }
}
