package com.bertholucci.data

import com.bertholucci.data.database.DatabaseDao
import com.bertholucci.data.model.EpisodesEmbeddedResponse
import com.bertholucci.data.model.ImageResponse
import com.bertholucci.data.model.RatingResponse
import com.bertholucci.data.model.ScheduleResponse
import com.bertholucci.data.model.ShowResponse
import com.bertholucci.data.repository.CardsRepositoryImpl
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
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
    fun `get weather information based on current location id`() = runBlockingTest {
        coEvery { api.getShowById(any()) } returns response

        agent.getShowById(0).collect {
            assertEquals(ShowMapper.mapToDomain(response), it)
        }
    }
}