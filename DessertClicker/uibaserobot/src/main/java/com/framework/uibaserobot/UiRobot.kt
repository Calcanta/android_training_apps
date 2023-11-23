package com.framework.uibaserobot

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.widget.MenuPopupWindow.MenuDropDownListView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.framework.utils.ViewShownIdlingResource
import com.framework.utils.containsSubstring
import com.framework.utils.scrollDownInNestedScrollView
import com.framework.utils.scrollInViewContainerToDefinedOption
import com.framework.utils.setIdlePolicies
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.materialswitch.MaterialSwitch
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.not

/**
 * Robot containing generic methods to perform actions and make assertions
 * on common UI elements; some methods use hierarchy matchers to identify
 * desired elements.
 */
class UiRobot {
    /** ACTIONS*/

    /**
     * Actions on system elements.
     */

    /**
     * Press the back button on the mobile phone (different from the back button on the app).
     */
    fun pressSystemBack(repetitions: Int = 1) {
        for (i in 1..repetitions) {
            pressBack()
        }
    }

    /**
     * Close soft keyboard.
     */
    fun closeKeyboard(@IdRes textFieldId: Int) {
        onView(withId(textFieldId)).perform(closeSoftKeyboard())
    }

    /**
     * Click on element, simple identification.
     */

    /**
     * Click on the menu item identified by its **position**.
     * Designed to be applied in MenuDropDownListView.
     */
    @SuppressLint("RestrictedApi")
    fun clickOnMenuItemByPosition(itemPosition: Int) {
        onData(anything()).inAdapterView(isAssignableFrom(MenuDropDownListView::class.java))
            .atPosition(itemPosition)
            .perform(click())
    }

    /**
     * Click on element with specific **id** and _clickable_ attribute = True.
     */
    fun clickOnElement(@IdRes elementId: Int) {
        onView(withId(elementId)).perform(click())
    }

    /**
     * Click on element with specific **text** and _clickable_ attribute = True.
     */
    fun clickOnElement(elementText: String) {
        onView(withText(elementText)).perform(click())
    }

    /**
     * Click on element with specific **id**, given **text** and _clickable_ attribute = True.
     */
    fun clickOnElement(@IdRes elementId: Int, elementText: String) {
        onView(
            allOf(
                withId(elementId),
                ViewMatchers.withContentDescription(elementText),
            )
        ).perform(click())
    }

    /**
     * Click on element with _clickable_ attribute = True,
     * the element is identified by the matcher entered as argument.
     */
    fun clickOnElement(matcher: Matcher<View>) {
        onView(matcher).perform(click())
    }

    /**
     * Click on element, hierarchy identification.
     */

    /**
     * Click on element with specific **id**, with _parent_ element identified by its **id**
     * and with _clickable_ attribute = True.
     */
    fun clickOnChild(@IdRes parentId: Int, @IdRes elementId: Int) {
        onView(
            allOf(
                withParent(withId(parentId)),
                withId(elementId),
            )
        ).perform(click())
    }

    /**
     * Click on element with specific **text**,  with _parent_ element identified by its **id**
     * and with _clickable_ attribute = True.
     */
    fun clickOnChild(@IdRes parentId: Int, elementText: String) {
        onView(
            allOf(
                withParent(withId(parentId)),
                withText(elementText),
            )
        ).perform(click())
    }

    /**
     * Click on element with specific **id**, ascendant of an element
     * identified by its **id** and with _clickable_ attribute = True.
     */
    fun clickOnAscendant(@IdRes ascendantId: Int, @IdRes descendantId: Int) {
        onView(
            allOf(
                withId(ascendantId),
                hasDescendant(withId(descendantId))
            )
        ).perform(click())
    }

    /**
     * Click on element with specific **id**, ascendant of an element
     * identified by its **text** and with _clickable_ attribute = True.
     */
    fun clickOnAscendant(@IdRes ascendantId: Int, descendantText: String) {
        onView(
            allOf(
                withId(ascendantId),
                hasDescendant(withText(descendantText))
            )
        ).perform(click())
    }

    /**
     * Click on element assignable to a given View type subclass, with _clickable_ attribute = True,
     * ascendant of an element identified by its **text**.
     *
     */
    fun <T : View> clickOnAscendant(ascendantClassType: Class<T>, descendantText: String) {
        onView(
            allOf(
                isAssignableFrom(ascendantClassType),
                hasDescendant(withText(descendantText))
            )
        ).perform(click())
    }

    /**
     * Click on element with a given **id**, with _clickable_ attribute = True,
     * descendant of an element identified by its **id**.
     */
    fun clickOnDescendant(@IdRes descendantId: Int, @IdRes ascendantId: Int) {
        onView(
            allOf(
                withId(descendantId),
                isDescendantOfA(withId(ascendantId))
            )
        ).perform(click())
    }

    /**
     * Click on element with specific **text**, with _clickable_ attribute = True,
     * descendant of an element identified by its **id**.
     */
    fun clickOnDescendant(descendantText: String, @IdRes ascendantId: Int) {
        onView(
            allOf(
                withText(descendantText),
                isDescendantOfA(withId(ascendantId))
            )
        ).perform(click())
    }

    /**
     * Click on element assignable to a View type subclass, with _clickable_ attribute = True,
     * ascendant of an element identified by its **text**.
     */
    fun <T : View> clickOnDescendant(
        descendantClassType: Class<T>,
        @IdRes ascendantId: Int
    ) {
        onView(
            allOf(
                isAssignableFrom(descendantClassType),
                isDescendantOfA(withId(ascendantId))
            )
        ).perform(click())
    }

    /**
     * Scroll, simple identification.
     */

    /**
     * Scroll to a reference option contained in a given View container.
     * Both the view container and the reference option must be identified by their **id**.
     * This function can be used to scroll to any option in a custom list/menu.
     */
    fun scrollToOptionInListContainer(
        @IdRes containerId: Int,
        @IdRes optionId: Int
    ) {
        onView(withId(containerId))
            .perform(
                scrollInViewContainerToDefinedOption(
                    containerId = containerId,
                    optionId = optionId
                )
            )
    }

    /**
     * Scroll down in a NestedScrollView. It uses an alternate definition of scroll down,
     * specific to the NestedScrollView.
     */
    fun scrollAllDownInNestedScrollView(nestedScrollViewMatcher: Matcher<View>) {
        onView(nestedScrollViewMatcher).perform(scrollDownInNestedScrollView)
    }

    /**
     * Scroll and click on element, simple identification.
     */

    /**
     * Scroll and click on element, with _clickable_ attribute = True,
     * identified by the matcher entered as argument.
     */
    fun scrollToAndClickOnElement(matcher: Matcher<View>) {
        onView(matcher).perform(scrollTo(), click())
    }

    /**
     * Scroll and click on element, hierarchy identification.
     */

    /**
     * Scroll and click on element with specific **id**, with _clickable_ attribute = True,
     * ascendant of an element identified by its **id**.
     */
    fun scrollAndClickOnAscendant(@IdRes ascendantId: Int, descendantText: String) {
        onView(
            allOf(
                withId(ascendantId),
                hasDescendant(withText(descendantText))
            )
        ).perform(scrollTo(), click())
    }

    /**
     * Check / uncheck element, simple identification.
     */

    /**
     * Check (Enable) Slider if unchecked, the slider is identified by the matcher passed as an argument.
     */
    fun setSliderOn(matcher: Matcher<View>) {
        val slider = onView(matcher)
        slider.check { view, _ ->
            if (view is MaterialSwitch) {
                val isChecked = view.isChecked
                if (!isChecked) {
                    view.isChecked = true
                }
            }
        }
    }

    /**
     * Uncheck (Disable) slider if checked, the slider is identified by the matcher passed as argument.
     */
    fun setSliderOff(matcher: Matcher<View>) {
        val slider = onView(matcher)
        slider.check { view, _ ->
            if (view is MaterialSwitch) {
                val isChecked = view.isChecked
                if (isChecked) {
                    view.isChecked = false
                }
            }
        }
    }

    /**
     * Check CheckBox if unchecked, the CheckBox is identified by the matcher passed as argument.
     */
    fun setCheckBoxOn(matcher: Matcher<View>) {
        val checkBox = onView(matcher)
        checkBox.check { view, _ ->
            if (view is MaterialCheckBox) {
                val isChecked = view.isChecked
                if (!isChecked) {
                    view.isChecked = true
                }
            }
        }
    }

    /**
     * Uncheck CheckBox if checked, the CheckBox is identified by the matcher passed as argument.
     */
    fun setCheckBoxOff(matcher: Matcher<View>) {
        val checkBox = onView(matcher)
        checkBox.check { view, _ ->
            if (view is MaterialCheckBox) {
                val isChecked = view.isChecked
                if (isChecked) {
                    view.isChecked = false
                }
            }
        }
    }

    /**
     * Actions in text fields, simple identification.
     */

    /**
     * In the text field with the given **id**, replace the text. Finally close the keyboard.
     */
    fun replaceTextOnTextFieldAndCloseKeyboard(
        @IdRes textFieldId: Int,
        textToEnter: String
    ) {
        onView(
            withId(textFieldId)
        ).perform(replaceText(textToEnter), closeSoftKeyboard())
    }

    /**
     * Actions in recycler view, simple identification.
     */

    /**
     * Click on an item within a certain **position** of a _RecyclerView_
     * identified by a given **Matcher**.
     */
    fun clickOnRecyclerElementByPosition(
        recyclerMatcher: Matcher<View>,
        positionOnRecycler: Int
    ) {
        onView(recyclerMatcher).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionOnRecycler,
                click()
            )
        )
    }

    /**
     * Actions in recycler view, hierarchy identification.
     */
    /**
     * From a _RecyclerView_, identified by its **id**, click the item that has a
     * descendant with the given **text**.
     */
    fun clickOnRecyclerViewItemWithTextOnDescendant(
        recyclerViewMatcher: Matcher<View>,
        textOnItemDescendant: String
    ) {
        onView(recyclerViewMatcher)
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText(textOnItemDescendant)),
                    click()
                )
            )
    }

    /**
     * Actions with shell commands.
     */

    /**
     * Enable airplane-mode.
     */
    fun turnOnAirplaneMode() {
        InstrumentationRegistry.getInstrumentation()
            .uiAutomation
            .executeShellCommand("cmd connectivity airplane-mode enable")
    }

    /**
     * Disable airplane-mode.
     */
    fun turnOffAirplaneMode() {
        InstrumentationRegistry.getInstrumentation()
            .uiAutomation
            .executeShellCommand("cmd connectivity airplane-mode disable")
    }

    /**
     * Enable WiFi Connection.
     */
    fun turnOnWiFi() {
        InstrumentationRegistry.getInstrumentation()
            .uiAutomation.executeShellCommand("svc wifi enable")
    }

    /**
     * Disable WiFi Connection.
     */
    fun turnOffWiFi() {
        InstrumentationRegistry.getInstrumentation()
            .uiAutomation.executeShellCommand("svc wifi disable")
    }

    /**
     * Enable Mobile Data Connection.
     */
    fun turnOnMobileData() {
        InstrumentationRegistry.getInstrumentation()
            .uiAutomation.executeShellCommand("svc data enable")
    }

    /**
     * Disable Mobile Data Connection.
     */
    fun turnOffMobileData() {
        InstrumentationRegistry.getInstrumentation()
            .uiAutomation.executeShellCommand("svc data disable")
    }

    /**
     * Execute the shell command passed as a formatted string parameter.
     */
    fun runShellCommand(command: String) {
        InstrumentationRegistry.getInstrumentation()
            .uiAutomation.executeShellCommand(command)
    }

    /** ASSERTIONS*/

    /**
     * Assertions on element, simple identification.
     */

    /**
     * Verifies that  a UI element with the given **text** is displayed.
     */
    fun assertElementIsDisplayed(contentText: String) {
        assertElementIsDisplayed(matcher = withText(contentText))
    }

    /**
     * Verifies that  a UI element with the given **id** is displayed.
     */
    fun assertElementIsDisplayed(@IdRes id: Int) {
        assertElementIsDisplayed(matcher = withId(id))
    }

    /**
     * Verifies that a UI element, with the given **id** and **text**, is displayed.
     */
    fun assertElementIsDisplayed(@IdRes id: Int, contentText: String) {
        val matcher = allOf(withId(id), withText(contentText))
        assertElementIsDisplayed(matcher = matcher)
    }

    /**
     * Verifies that a UI element, containing a given **substring**, is displayed.
     */
    fun assertElementWithSubstringIsDisplayed(elementSubstring: String) {
        val matcher = withText(containsSubstring(expectedSubstring = elementSubstring))
        assertElementIsDisplayed(matcher = matcher)
    }

    /**
     * Verifies that a UI element, with a given **id** and containing the specified **substring**, is displayed.
     */
    fun assertElementWithSubstringIsDisplayed(
        elementSubstring: String,
        @IdRes elementId: Int
    ) {
        val matcher = allOf(
            withId(elementId),
            withText(containsSubstring(expectedSubstring = elementSubstring))
        )
        assertElementIsDisplayed(matcher = matcher)
    }

    /**
     * Assert ui element with specific **view matcher** is shown.
     */
    fun assertElementIsDisplayed(matcher: Matcher<View>) {
        onView(matcher).check(matches(isDisplayed()))
    }

    /**
     * Assertions on element, with idle resource.
     * */

    /**
     * Verifies that a UI element, identified by the given _matcher_, is displayed.
     * It uses a custom idle resource to validate asynchronous operations more reliably.
     */
    fun waitViewToShownWithIdleResource(viewMatcher: Matcher<View>, timeOutMillis: Long = 3000L) {
        setIdlePolicies(timeOutMillis)
        // Register the custom IdlingResource.
        val idlingResource = ViewShownIdlingResource(viewMatcher)
        try {
            IdlingRegistry.getInstance().register(idlingResource)
            // Perform assertion.
            onView(viewMatcher).check(matches(isDisplayed()))
        } finally {
            // Unregister the IdlingResource after the assertion.
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }

    /**
     * Verifies that a UI element, identified by the given _matcher_, is NOT displayed.
     * It uses a custom idle resource to validate asynchronous operations more reliably.
     */
    fun waitIsNotShownWithIdleResource(
        viewMatcher: Matcher<View>,
        timeOutMillis: Long = 3000L
    ) {
        setIdlePolicies(timeOutMillis)
        // Register the custom IdlingResource.
        val idlingResource = ViewShownIdlingResource(viewMatcher)
        try {
            IdlingRegistry.getInstance().register(idlingResource)
            // Perform assertion.
            onView(viewMatcher).check(matches(not(isDisplayed())))
        } finally {
            // Unregister the IdlingResource after the assertion.
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }

    /**
     * Verifies that a UI element, identified by the given _matcher_, does not exist.
     * It uses a custom idle resource to validate asynchronous operations more reliably.
     */
    fun waitDoesNotExistWithIdleResource(
        viewMatcher: Matcher<View>,
        timeOutMillis: Long = 3000L
    ) {
        setIdlePolicies(timeOutMillis)
        // Register the custom IdlingResource.
        val idlingResource = ViewShownIdlingResource(viewMatcher)
        try {
            IdlingRegistry.getInstance().register(idlingResource)
            // Perform assertion.
            onView(viewMatcher).check(doesNotExist())
        } finally {
            // Unregister the IdlingResource after the assertion.
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }

    /**
     * Assertions on element, hierarchy identification.
     * */

    /**
     * Verifies that a UI element, with a given **id**,
     * descendant of an element identified by its **id**, is displayed.
     */
    fun assertDescendantIsDisplayed(@IdRes descendantId: Int, @IdRes ascendantId: Int) {
        val matcher = allOf(
            withId(descendantId),
            isDescendantOfA(withId(ascendantId))
        )
        assertElementIsDisplayed(matcher = matcher)
    }

    /**
     * Verifies that a UI element, with a given **text**,
     * descendant of an element identified by its **id**, is displayed.
     */
    fun assertDescendantIsDisplayed(descendantText: String, @IdRes ascendantId: Int) {
        val matcher = allOf(
            withText(descendantText),
            isDescendantOfA(withId(ascendantId))
        )
        assertElementIsDisplayed(matcher = matcher)
    }

    /**
     * Verify that a UI element, containing the given **substring**,
     * descendant of an element identified by its **id**, is displayed.
     */
    fun assertDescendantWithSubstringIsDisplayed(
        descendantSubstring: String,
        @IdRes ascendantId: Int
    ) {
        val matcher = allOf(
            withText(containsSubstring(expectedSubstring = descendantSubstring)),
            isDescendantOfA(withId(ascendantId))
        )
        assertElementIsDisplayed(matcher = matcher)
    }

    /**
     * Verify that a UI element with the given **id**, containing the provided **substring**,
     * and descendant of an element identified by its **id**, is displayed.
     */
    fun assertDescendantWithSubstringIsDisplayed(
        descendantSubstring: String,
        @IdRes descendantId: Int,
        @IdRes ascendantId: Int
    ) {
        val matcher = allOf(
            withId(descendantId),
            withText(containsSubstring(expectedSubstring = descendantSubstring)),
            isDescendantOfA(withId(ascendantId)),
        )
        assertElementIsDisplayed(matcher = matcher)
    }

    /**
     * Check / uncheck assertions on element, simple identification.
     * */

    /**
     * Verify a Slider with the given **matcher** is enabled.
     */
    fun assertCheckBoxIsChecked(matcher: Matcher<View>) {
        onView(matcher).check(matches(isChecked()))
    }

    /**
     * Verify a checkbox with the given matcher is unchecked.
     */
    fun assertCheckBoxIsUnchecked(matcher: Matcher<View>) {
        onView(matcher).check(matches(not(isChecked())))
    }

    /**
     * Assertions in recycler view, simple identification.
     * */

    /**
     * Verifies that a _Material Card_ is displayed within a specific _index_ of a _Recycler View_
     * identified by the given id.
     */
    fun assertMaterialCardViewOnRecyclerViewPositionIsVisible(
        @IdRes recyclerViewId: Int,
        positionAtRecyclerView: Int,
        @IdRes materialCardViewId: Int
    ) {
        val recyclerView = onView(withId(recyclerViewId))
        // Perform the RecyclerView action to iterate through all items.
        recyclerView.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionAtRecyclerView,
                object : ViewAction {
                    override fun getConstraints(): Matcher<View> {
                        return isAssignableFrom(RecyclerView::class.java)
                    }

                    override fun getDescription(): String {
                        return "Check if any MaterialCardView is shown"
                    }

                    override fun perform(uiController: UiController, view: View) {
                        val materialCardView =
                            view.findViewById<MaterialCardView>(materialCardViewId)
                        matches(isDisplayed()).check(materialCardView, null)
                    }
                })
        )
    }

    /**
     * Verifies that a _Material Card_ is NOT displayed within a specific _index_ of a _Recycler View_
     * identified by the given id.
     */
    fun assertMaterialCardViewOnRecyclerViewPositionIsNotVisible(
        @IdRes recyclerViewId: Int,
        positionAtRecyclerView: Int,
        @IdRes materialCardViewId: Int
    ) {
        val recyclerView = onView(withId(recyclerViewId))
        // Perform the RecyclerView action to iterate through all items.
        recyclerView.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionAtRecyclerView,
                object : ViewAction {
                    override fun getConstraints(): Matcher<View> {
                        return isAssignableFrom(RecyclerView::class.java)
                    }

                    override fun getDescription(): String {
                        return "Check if any MaterialCardView is shown"
                    }

                    override fun perform(uiController: UiController, view: View) {
                        val materialCardView =
                            view.findViewById<MaterialCardView>(materialCardViewId)
                        matches(not(isDisplayed())).check(materialCardView, null)
                    }
                })
        )
    }
}