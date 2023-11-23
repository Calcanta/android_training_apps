package com.example.dessertclicker

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.example.dessertclicker.Constants.CONTEXT
import com.framework.uibaserobot.UiComposeRobot

internal fun uiAppRobot(func: UiAppRobot.() -> Unit) =
    UiAppRobot(baseComposeRobot = UiComposeRobot()).apply { func() }

class UiAppRobot(private val baseComposeRobot: UiComposeRobot) {
    fun clickOnDessertButton(composeTestRule: ComposeContentTestRule): UiAppRobot {
        baseComposeRobot.clickOnElement(
            composeTestRule = composeTestRule,
            elementDescription = "dessert_button"
        )
        return this
    }

    fun assertDessertsQuantityIsShown(
        composeTestRule: ComposeContentTestRule,
        quantity: Int
    ): UiAppRobot {
        baseComposeRobot.assertElementIsDisplayed(
            composeTestRule = composeTestRule,
            contentText = quantity.toString()
        )
        return this
    }

    fun assertDessertsQuantityPhraseIsShown(
        composeTestRule: ComposeContentTestRule
    ): UiAppRobot {
        val matcher =
            hasClickAction() and hasText(CONTEXT.getString(R.string.dessert_sold)) and hasText("1")
        baseComposeRobot.assertElementIsDisplayed(
            composeTestRule = composeTestRule,
            matcher = matcher
        )
        return this
    }
}