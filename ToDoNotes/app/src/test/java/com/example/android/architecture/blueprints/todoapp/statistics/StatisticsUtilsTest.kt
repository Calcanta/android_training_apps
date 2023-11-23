package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class StatisticsUtilsTest {
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        // Create an active task
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = false)
        )
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result
        assertThat(result.completedTasksPercent, `is`(equalTo(0f)))
        assertThat(result.activeTasksPercent, `is`(equalTo(100f)))
    }

    @Test
    fun getActiveAndCompletedStats_NoActive_returnsZeroHundred() {
        // Create a completed active task
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true)
        )
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result
        assertThat(result.completedTasksPercent, `is`(equalTo(100f)))
        assertThat(result.activeTasksPercent, `is`(equalTo(0f)))
    }

    @Test
    fun getActiveAndCompletedStats_ActiveAndComplete_returnsSixtyForty() {
        // Create a completed active task
        val tasks = listOf<Task>(
            Task("title_1", "description_1", isCompleted = true),
            Task("title_2", "description_2", isCompleted = true),
            Task("title_3", "description_3", isCompleted = false),
            Task("title_4", "description_4", isCompleted = false),
            Task("title_5", "description_5", isCompleted = false)
        )
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result
        assertThat(result.completedTasksPercent, `is`(equalTo(40f)))
        assertThat(result.activeTasksPercent, `is`(equalTo(60f)))
    }

    @Test
    fun getActiveAndCompletedStats_emptyList_returnsZeroes() {
        // Create a completed active task
        val tasks = listOf<Task>()
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result
        assertThat(result.completedTasksPercent, `is`(equalTo(0f)))
        assertThat(result.activeTasksPercent, `is`(equalTo(0f)))
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeroes() {
        // Create a completed active task
        val tasks = null
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result
        assertThat(result.completedTasksPercent, `is`(equalTo(0f)))
        assertThat(result.activeTasksPercent, `is`(equalTo(0f)))
    }
}