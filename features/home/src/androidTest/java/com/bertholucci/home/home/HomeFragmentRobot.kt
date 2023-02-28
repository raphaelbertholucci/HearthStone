package com.bertholucci.home.home

import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import com.bertholucci.domain.interactor.GetCards
import com.bertholucci.domain.model.Card
import com.bertholucci.home.R
import com.bertholucci.home.extensions.checkRecyclerViewItem
import com.bertholucci.home.extensions.hasText
import com.bertholucci.home.helpers.Check
import com.bertholucci.home.helpers.Execute
import com.bertholucci.home.helpers.Setup
import com.bertholucci.home.ui.home.HomeFragment
import com.bertholucci.home.ui.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun setupView(func: HomeFragmentRobot.() -> Unit) = HomeFragmentRobot().apply(func)

class HomeFragmentRobot : Setup<HomeFragmentRobotExecute, HomeFragmentRobotCheck> {

    private val getCards: GetCards = mockk(relaxed = true)

    init {
        startKoin {
            modules(
                listOf(
                    module { viewModel { HomeViewModel(getCards) } }
                )
            )
        }
    }

    override fun executeCreator() = HomeFragmentRobotExecute()

    override fun checkCreator() = HomeFragmentRobotCheck()

    override fun launch() {
        FragmentScenario.launchInContainer(
            fragmentClass = HomeFragment::class.java,
            fragmentArgs = bundleOf("name" to "RLK_Prologue_CS2_092e"),
            themeResId = R.style.Theme_App
        )
    }

    fun mockCards() {
        coEvery {
            getCards.executeUseCase(Unit)
        } returns flow {
            emit(mock)
        }
    }
}

class HomeFragmentRobotExecute : Execute<HomeFragmentRobotCheck> {
    override fun checkCreator() = HomeFragmentRobotCheck()
}

class HomeFragmentRobotCheck : Check {

    fun verifyStaticTexts() {
        R.id.tv_title.hasText(R.string.app_name)
    }

    fun verifyCards() {
        R.id.rv_cards.checkRecyclerViewItem<AppCompatTextView>(0, R.id.tv_title) { view ->
            view.text == "Blessing of Kings"
        }
        R.id.rv_cards.checkRecyclerViewItem<AppCompatTextView>(0, R.id.tv_type) { view ->
            view.text == "Spell"
        }
        R.id.rv_cards.checkRecyclerViewItem<AppCompatTextView>(0, R.id.tv_class) { view ->
            view.text == "Paladin"
        }
        R.id.rv_cards.checkRecyclerViewItem<AppCompatTextView>(0, R.id.tv_set) { view ->
            view.text == "Basic"
        }
    }

}

val mock = listOf(
    Card(
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
)