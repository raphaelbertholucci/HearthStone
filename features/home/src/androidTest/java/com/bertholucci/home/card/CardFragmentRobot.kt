package com.bertholucci.home.card

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import com.bertholucci.domain.interactor.GetCardByName
import com.bertholucci.domain.model.Card
import com.bertholucci.home.R
import com.bertholucci.home.extensions.hasText
import com.bertholucci.home.helpers.Check
import com.bertholucci.home.helpers.Execute
import com.bertholucci.home.helpers.Setup
import com.bertholucci.home.ui.card.CardFragment
import com.bertholucci.home.ui.card.CardViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun setupView(func: CardFragmentRobot.() -> Unit) = CardFragmentRobot().apply(func)

class CardFragmentRobot : Setup<CardFragmentRobotExecute, CardFragmentRobotCheck> {

    private val getCardByName: GetCardByName = mockk(relaxed = true)

    init {
        startKoin {
            modules(
                listOf(
                    module { viewModel { CardViewModel("Blessing of Kings", getCardByName) } }
                )
            )
        }
    }

    override fun executeCreator() = CardFragmentRobotExecute()

    override fun checkCreator() = CardFragmentRobotCheck()

    override fun launch() {
        FragmentScenario.launchInContainer(
            fragmentClass = CardFragment::class.java,
            fragmentArgs = bundleOf("name" to "RLK_Prologue_CS2_092e"),
            themeResId = R.style.Theme_App
        )
    }

    fun mockCard() {
        coEvery {
            getCardByName.executeUseCase("Blessing of Kings")
        } returns flow {
            emit(mock)
        }
    }
}

class CardFragmentRobotExecute : Execute<CardFragmentRobotCheck> {
    override fun checkCreator() = CardFragmentRobotCheck()
}

class CardFragmentRobotCheck : Check {

    fun verifyStaticTexts() {
        R.id.tv_flavor_label.hasText(R.string.card_flavor)
        R.id.tv_description_label.hasText(R.string.card_description)
    }

    fun verifyTexts() {
        R.id.tv_name.hasText(mock.name)
        R.id.tv_set.hasText(mock.cardSet)
        R.id.tv_type.hasText(mock.type)
        R.id.tv_faction.hasText(mock.faction)
        R.id.tv_rarity.hasText(mock.rarity)
        R.id.tv_attack.hasText(mock.attack)
        R.id.tv_cost.hasText(mock.cost)
        R.id.tv_health.hasText(mock.health)
        R.id.tv_flavor.hasText(mock.flavor)
        R.id.tv_description.hasText(mock.text)
    }
}

val mock = Card(
    id = "RLK_Prologue_CS2_092e",
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