package com.example.android.architecture.blueprints.todoapp

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * This is a fairly complicated method. It creates a Kotlin extension function called getOrAwaitValue
 * which adds an observer, gets the LiveData value, and then cleans up the observer—basically a short,
 * reusable version of the observeForever code shown above. For a full explanation of this class, check out this blog post:
 * url: https://medium.com/androiddevelopers/unit-testing-livedata-and-other-common-observability-problems-bb477262eb04
 */
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()
        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }
    } finally {
        // Whatever happens, don't forget to remove the observer!
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}