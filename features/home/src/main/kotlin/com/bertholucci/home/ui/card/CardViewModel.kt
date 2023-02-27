package com.bertholucci.home.ui.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.domain.helper.HearthStoneResponse
import com.bertholucci.domain.interactor.GetCardByName
import com.bertholucci.domain.model.Card
import com.bertholucci.home.extensions.response

class CardViewModel(
    private val cardName: String,
    private val getCardByName: GetCardByName
) : ViewModel() {

    private val _card = MutableLiveData<HearthStoneResponse<Card>>()
    val card: LiveData<HearthStoneResponse<Card>>
        get() = _card

    init {
        getCardDetails()
    }

    fun getCardDetails() {
        getCardByName(cardName).response(_card, viewModelScope)
    }
}
