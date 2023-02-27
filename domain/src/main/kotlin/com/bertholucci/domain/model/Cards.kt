package com.bertholucci.domain.model

data class Card(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val flavor: String = "",
    val text: String = "",
    val playerClass: String = "",
    val cardSet: String = "",
    val type: String = "",
    val rarity: String = "",
    val faction: String = "",
    val cost: String = "",
    val attack: String = "",
    val health: String = ""
)