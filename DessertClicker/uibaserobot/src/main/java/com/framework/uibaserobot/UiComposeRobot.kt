package com.framework.uibaserobot

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick

class UiComposeRobot {
    /** ACTIONS*/

    /**
     * Click on element.
     */

    /**
     * Click on element with click action and specific **content description**.
     */
    fun clickOnElement(composeTestRule: ComposeContentTestRule, elementDescription: String) {
        val matcher = hasClickAction() and hasContentDescription(elementDescription)
        clickOnElement(composeTestRule = composeTestRule, matcher = matcher)
    }

    /**
     * Click on element with click action and a given **matcher**.
     */
    fun clickOnElement(
        composeTestRule: ComposeContentTestRule,
        matcher: SemanticsMatcher,
        useUnmergedTree: Boolean = false
    ) {
        composeTestRule.onNode(
            matcher = matcher, useUnmergedTree = useUnmergedTree
        ).performClick()
    }

    /** ASSERTIONS*/

    /**
     * Assertions on element.
     */

    /**
     * Verifies that  a UI element with the given **text** is displayed.
     */
    fun assertElementIsDisplayed(
        composeTestRule: ComposeContentTestRule,
        contentText: String,
        substring: Boolean = false
    ) {
        assertElementIsDisplayed(
            composeTestRule = composeTestRule,
            hasText(text = contentText, substring = substring)
        )
    }

    /**
     * Assert ui element with specific **Semantics Matcher** is shown.
     */
    fun assertElementIsDisplayed(
        composeTestRule: ComposeContentTestRule,
        matcher: SemanticsMatcher,
        useUnmergedTree: Boolean = false
    ) {
        composeTestRule.onNode(
            matcher = matcher,
            useUnmergedTree = useUnmergedTree
        ).assertIsDisplayed()
    }

    /**
     * Assert ui element with specific **Semantics Matcher** Exists.
     */
    fun assertElementExists(
        composeTestRule: ComposeContentTestRule,
        matcher: SemanticsMatcher,
        useUnmergedTree: Boolean = false
    ) {
        composeTestRule.onNode(
            matcher = matcher,
            useUnmergedTree = useUnmergedTree
        ).assertExists()
    }

    /**
     * Assert ui element with specific **Semantics Matcher** Exists.
     */
    fun assertElementDoesNotExists(
        composeTestRule: ComposeContentTestRule,
        matcher: SemanticsMatcher,
        useUnmergedTree: Boolean = false
    ) {
        composeTestRule.onNode(
            matcher = matcher,
            useUnmergedTree = useUnmergedTree
        ).assertDoesNotExist()
    }
}