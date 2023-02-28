package com.bertholucci.data

import com.bertholucci.data.mapper.CardMapper
import com.bertholucci.data.model.CardResponse
import com.bertholucci.data.repository.CardsRepositoryImpl
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class CardsRepositoryImplTest : BaseTest<CardsRepositoryImpl>() {

    @RelaxedMockK
    private lateinit var api: HearthStoneApi

    override fun init() {
        agent = CardsRepositoryImpl(api)
    }

    @Test
    fun `get card based on the name passed`() = runTest {
        coEvery { api.getShowByName(any()) } returns response

        agent.getCardByName("Blessing").collect {
            assertEquals(CardMapper.mapToDomain(response.first()), it)
        }
    }
}

val response = listOf(
    CardResponse(
        id = "1234",
        name = "Blessing of Kings",
        image = "image",
        flavor = "Given the number of kings who have been assassinated, are you sure you want their blessing?",
        text = "Give a minion +4/+4. <i>(+4 Attack/+4 Health)</i>",
        playerClass = "Paladin",
        cardSet = "Basic",
        type = "Spell",
        rarity = "Free",
        faction = "Neutral",
        cost = "4",
        attack = "10",
        health = "20"
    )
)