package com.framework.utils

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * Custom Matcher to assert whether a View element contains a defined substring in its text content.
 */
internal fun containsSubstring(expectedSubstring: String): Matcher<String> {
    return object : TypeSafeMatcher<String>() {
        override fun describeTo(description: Description?) {
            description?.appendText("contains substring: $expectedSubstring")
        }

        override fun matchesSafely(item: String?): Boolean {
            return item?.contains(expectedSubstring) == true
        }
    }
}