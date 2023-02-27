package com.bertholucci.data.mapper

import com.bertholucci.data.model.CardResponse
import com.bertholucci.domain.model.Card

object CardMapper : BaseMapper<CardResponse, Card> {

    override fun mapFromDomain(domain: Card): CardResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: CardResponse): Card {
        return Card(
            id = response.id ?: "",
            image = response.image ?: "",
            name = response.name ?: "",
            flavor = response.flavor ?: "",
            text = response.text ?: "",
            playerClass = response.playerClass ?: "",
            cardSet = response.cardSet ?: "",
            type = response.type ?: "",
            rarity = response.rarity ?: "",
            faction = response.faction ?: "",
            cost = response.cost ?: "",
            attack = response.attack ?: "",
            health = response.health ?: ""
        )
    }
}