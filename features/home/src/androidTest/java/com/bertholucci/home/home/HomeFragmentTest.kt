package com.bertholucci.home.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun verifyStaticTexts() {
        setupView {} execute {} check {
            verifyStaticTexts()
        }
    }

    @Test
    fun typeOneTrackName_AndCheckIfSongIsShown() {
        setupView {
            mockCards()
        } execute {} check {
            verifyCards()
        }
    }
}