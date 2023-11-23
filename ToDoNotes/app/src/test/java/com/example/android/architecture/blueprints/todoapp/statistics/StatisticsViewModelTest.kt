package com.example.android.architecture.blueprints.todoapp.statistics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.MainCoroutineRule
import com.example.android.architecture.blueprints.todoapp.ServiceLocator.tasksRepository
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import com.example.android.architecture.blueprints.todoapp.tasks.TasksViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StatisticsViewModelTest{
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Subject under test
    private lateinit var statisticsViewModel: StatisticsViewModel

    // Use a fake repository to be injected into the view model.
    private lateinit var tasksRepository: FakeTestRepository

    @Before
    fun setupStatisticsViewModel() {
        // Initialise the repository with no tasks.
        tasksRepository = FakeTestRepository()

        statisticsViewModel = StatisticsViewModel(tasksRepository)
    }

    @Test
    fun loadTasks_loading() {
        // Pause dispatcher so you can verify initial values.
        mainCoroutineRule.pauseDispatcher()

        // Load the task in the view model.
        statisticsViewModel.refresh()

        // Then progress indicator is shown.
        assertThat(statisticsViewModel.dataLoading.getOrAwaitValue(), `is`(true))

        // Execute pending coroutines actions.
        mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden.
        assertThat(statisticsViewModel.dataLoading.getOrAwaitValue(), `is`(false))
    }
    @Test
    fun loadStatisticsWhenTasksAreUnavailable_callErrorToDisplay(){
        // Set error to true.
        tasksRepository.setReturnError(true)
        statisticsViewModel.refresh()

        // No tasks available.
        assertThat(statisticsViewModel.empty.getOrAwaitValue(), `is`(true))
        // Returns error (which triggers an error message to be shown).
        assertThat(statisticsViewModel.error.getOrAwaitValue(), `is`(true))
    }
}