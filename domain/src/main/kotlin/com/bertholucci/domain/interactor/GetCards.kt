package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.Card
import com.bertholucci.domain.repository.CardsRepository
import kotlinx.coroutines.flow.Flow

class GetCards(private val repository: CardsRepository) : UseCase<Unit, List<Card>>() {

    override fun executeUseCase(requestValues: Unit): Flow<List<Card>> {
        return repository.getCards()
    }
}