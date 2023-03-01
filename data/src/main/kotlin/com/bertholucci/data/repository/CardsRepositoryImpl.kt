package com.bertholucci.data.repository

import com.bertholucci.data.HearthStoneApi
import com.bertholucci.data.extensions.groupCards
import com.bertholucci.data.mapper.CardMapper
import com.bertholucci.domain.model.Card
import com.bertholucci.domain.repository.CardsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardsRepositoryImpl(
    private val api: HearthStoneApi
) : CardsRepository {

    override fun getCards(): Flow<List<Card>> {
        return flow {
            emit(api.getCards().groupCards())
        }
    }

    override fun getCardByName(name: String): Flow<Card> {
        return flow {
            emit(CardMapper.mapToDomain(api.getCardByName(name).first()))
        }
    }
}