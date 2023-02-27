package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.Card
import com.bertholucci.domain.repository.CardsRepository
import kotlinx.coroutines.flow.Flow

class GetCardByName(private val repository: CardsRepository) : UseCase<String, Card>() {

    override fun executeUseCase(requestValues: String): Flow<Card> {
        return repository.getCardByName(name = requestValues)
    }
}