package com.example.android.architecture.blueprints.todoapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * MainCoroutineRule extends TestWatcher, which implements the TestRule interface.
 * This is what makes MainCoroutineRule a JUnit rule.
 * The starting and finished methods match what you wrote in your @Before and @After functions.
 * They also run before and after each test.
 */
@ExperimentalCoroutinesApi
class MainCoroutineRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()):
    TestWatcher(),
    TestCoroutineScope by TestCoroutineScope(dispatcher) {
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}