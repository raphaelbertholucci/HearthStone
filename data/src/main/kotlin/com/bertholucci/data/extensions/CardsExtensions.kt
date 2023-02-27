package com.bertholucci.data.extensions

import com.bertholucci.data.mapper.CardMapper
import com.bertholucci.data.model.CardResponse
import com.bertholucci.data.model.CardsResponse
import com.bertholucci.domain.model.Card

fun List<CardResponse>?.mapToCard(): List<Card> {
    return this?.map { CardMapper.mapToDomain(it) } ?: emptyList()
}

fun CardsResponse.groupCards(): List<Card> {
    return mutableListOf<Card>().apply {
        basic?.let { addAll(it.mapToCard()) }
        classic?.let { addAll(it.mapToCard()) }
        hallOfFame?.let { addAll(it.mapToCard()) }
        missions?.let { addAll(it.mapToCard()) }
        naxxramas?.let { addAll(it.mapToCard()) }
        goblinVsGnomes?.let { addAll(it.mapToCard()) }
        blackrockMountain?.let { addAll(it.mapToCard()) }
        theGrandTournament?.let { addAll(it.mapToCard()) }
        credits?.let { addAll(it.mapToCard()) }
        heroSkins?.let { addAll(it.mapToCard()) }
        tavernBrawl?.let { addAll(it.mapToCard()) }
        theLeagueOfExplorer?.let { addAll(it.mapToCard()) }
        whispersOfTheOldGoods?.let { addAll(it.mapToCard()) }
        oneNightInKarazhan?.let { addAll(it.mapToCard()) }
        meanStreetsOfGadgetzan?.let { addAll(it.mapToCard()) }
        journeyToUnGoro?.let { addAll(it.mapToCard()) }
        knightsOfTheFrozenThrone?.let { addAll(it.mapToCard()) }
        koboldsAndCatacombs?.let { addAll(it.mapToCard()) }
        theWitchwood?.let { addAll(it.mapToCard()) }
        theBoomsdayProject?.let { addAll(it.mapToCard()) }
        rastakhansRumble?.let { addAll(it.mapToCard()) }
        riseOfShadows?.let { addAll(it.mapToCard()) }
        tavernsOfTime?.let { addAll(it.mapToCard()) }
        saviorsOfUldum?.let { addAll(it.mapToCard()) }
        descentOfDragons?.let { addAll(it.mapToCard()) }
        galakrondsAwakening?.let { addAll(it.mapToCard()) }
        ashesOfOutland?.let { addAll(it.mapToCard()) }
        wildEvent?.let { addAll(it.mapToCard()) }
        scholmanceAcademy?.let { addAll(it.mapToCard()) }
        battlegrounds?.let { addAll(it.mapToCard()) }
        demonHunterInitiate?.let { addAll(it.mapToCard()) }
        madnessAtTheDarkmoonFaire?.let { addAll(it.mapToCard()) }
        forgedInTheBarrens?.let { addAll(it.mapToCard()) }
        legacy?.let { addAll(it.mapToCard()) }
        core?.let { addAll(it.mapToCard()) }
        vanilla?.let { addAll(it.mapToCard()) }
        wailingCaverns?.let { addAll(it.mapToCard()) }
        unitedInStormwind?.let { addAll(it.mapToCard()) }
        mercenaries?.let { addAll(it.mapToCard()) }
        fracturedInAlteracValley?.let { addAll(it.mapToCard()) }
        voyageToTheSunkenCity?.let { addAll(it.mapToCard()) }
        unknown?.let { addAll(it.mapToCard()) }
        murderAtCastleNathria?.let { addAll(it.mapToCard()) }
        theGrandTournament?.let { addAll(it.mapToCard()) }
        marchOfTheLichKing?.let { addAll(it.mapToCard()) }
        pathOfArthas?.let { addAll(it.mapToCard()) }
    }
}