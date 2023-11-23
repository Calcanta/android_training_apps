package com.example.dessertclicker

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.dessertclicker.ui.theme.DessertClickerTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DessertClickerTheme {
                DessertClickerApp()
            }
        }
    }

    @Test
    fun dessertsSoldSum() {
        uiAppRobot {
            clickOnDessertButton(composeTestRule = composeTestRule)
            assertDessertsQuantityIsShown(composeTestRule = composeTestRule, quantity = 1)
            assertDessertsQuantityPhraseIsShown(composeTestRule = composeTestRule)
        }
    }
}