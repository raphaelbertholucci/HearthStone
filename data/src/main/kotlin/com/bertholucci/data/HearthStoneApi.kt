package com.bertholucci.data

import com.bertholucci.data.model.CardResponse
import com.bertholucci.data.model.CardsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HearthStoneApi {

    @GET("cards")
    suspend fun getCards(): CardsResponse

    @GET("cards/{name}")
    suspend fun getCardByName(@Path("name") name: String): List<CardResponse>
}