package com.framework.utils

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import androidx.test.espresso.ViewFinder
import org.hamcrest.Matcher
import java.lang.reflect.Field
import java.util.concurrent.TimeUnit

/**
 * Class to create an Idling Resource based on a viewMatcher, implementing the IdlingResource interface,
 * used to reduce synchronization related problems.
 */
internal class ViewShownIdlingResource(private val viewMatcher: Matcher<View>) : IdlingResource {
    private var resourceCallback: ResourceCallback? = null

    override fun isIdleNow(): Boolean {
        val view = getView(viewMatcher)
        val idle = view == null || view.isShown

        if (idle && resourceCallback != null) {
            resourceCallback!!.onTransitionToIdle()
        }

        return idle
    }

    override fun registerIdleTransitionCallback(resourceCallback: ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

    override fun getName(): String {
        return this.toString() + viewMatcher.toString()
    }

    private fun getView(viewMatcher: Matcher<View>): View? {
        return try {
            val viewInteraction = onView(viewMatcher)
            val finderField: Field = viewInteraction.javaClass.getDeclaredField("viewFinder")
            finderField.isAccessible = true
            val finder: ViewFinder = finderField.get(viewInteraction) as ViewFinder
            finder.view
        } catch (e: Exception) {
            null
        }
    }
}

// The method sets specific idle policies related to timeout.
internal fun setIdlePolicies(timeoutMillis: Long) {
    IdlingPolicies.setIdlingResourceTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
    // Set the synchronization policy to WAIT_FOR_IDLE.
    IdlingPolicies.setMasterPolicyTimeout(timeoutMillis * 2, TimeUnit.MILLISECONDS)
    IdlingPolicies.setIdlingResourceTimeout(timeoutMillis * 2, TimeUnit.MILLISECONDS)
}