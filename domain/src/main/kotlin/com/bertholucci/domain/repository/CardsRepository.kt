package com.bertholucci.domain.repository

import com.bertholucci.domain.model.Card
import kotlinx.coroutines.flow.Flow

interface CardsRepository {
    fun getCards(): Flow<List<Card>>
    fun getCardByName(name: String): Flow<Card>
}