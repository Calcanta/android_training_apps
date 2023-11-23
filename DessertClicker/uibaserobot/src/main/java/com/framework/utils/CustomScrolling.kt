package com.framework.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

/**
 * Alternative definition of scrolling down in a NestedScrollView.
 */
internal val scrollDownInNestedScrollView: ViewAction = object : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return allOf(
            withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
            isAssignableFrom(NestedScrollView::class.java)
        )
    }

    override fun getDescription(): String {
        return "Scroll down"
    }

    override fun perform(uiController: UiController, view: View) {
        val scrollView = view as NestedScrollView
        // Modify the maxScrollAmount calculation to customize the scrolling length.
        val maxScrollAmount = scrollView.getChildAt(0).height - scrollView.height
        scrollView.scrollTo(0, maxScrollAmount)
        uiController.loopMainThreadUntilIdle()
    }
}

/**
 * Scroll to an item in a given View container.
 * Both, the View container and the item must be identified by their **id**.
 */
internal fun scrollInViewContainerToDefinedOption(
    @IdRes containerId: Int,
    @IdRes optionId: Int
): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return allOf(
                isAssignableFrom(View::class.java),
                withId(containerId)
            )
        }

        override fun getDescription(): String {
            return "scroll to last option"
        }

        override fun perform(uiController: UiController, view: View) {
            val linearLayout = view.findViewById<View>(containerId)
            val lastOption = linearLayout.findViewById<View>(optionId)
            if (lastOption != null) {
                val scrollY = (linearLayout.height + lastOption.height) - lastOption.top
                view.scrollTo(0, scrollY)
            }
        }
    }
}