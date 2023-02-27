import com.bertholucci.domain.interactor.GetCardByName
import com.bertholucci.domain.model.Card
import com.bertholucci.domain.repository.CardsRepository
import helpers.BaseTest
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetShowsByIdTest : BaseTest<GetCardByName>() {

    @RelaxedMockK
    private lateinit var repository: CardsRepository

    override fun init() {
        agent = GetCardByName(repository)
    }

    @Test
    fun `get card by name`() = runBlockingTest {
        coEvery { repository.getCardByName(any()) } returns flow {
            emit(mock)
        }

        val request = "Blessing of Kings"

        agent(request)

        agent.invoke(request).collect { result ->
            assertEquals(mock, result)
        }
    }
}

val mock = Card(
    name = "Blessing of Kings",
    image = "image",
    flavor = "Given the number of kings who have been assassinated, are you sure you want their blessing?",
    text = "Give a minion +4/+4. <i>(+4 Attack/+4 Health)</i>",
    cardSet = "Basic",
    type = "Spell",
    rarity = "Free",
    faction = "Neutral",
    cost = 4,
    attack = 10,
    health = 20
)