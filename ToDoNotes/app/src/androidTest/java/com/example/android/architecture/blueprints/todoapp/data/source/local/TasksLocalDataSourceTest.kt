package com.example.android.architecture.blueprints.todoapp.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class TasksLocalDataSourceTest {
    private lateinit var tasksLocalDataSource: TasksLocalDataSource
    private lateinit var database: ToDoDatabase

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ToDoDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        tasksLocalDataSource = TasksLocalDataSource(database.taskDao(), Dispatchers.Main)
    }

    @After
    fun cleanup() {
        database.close()
    }

    // runBlocking is used here because of https://github.com/Kotlin/kotlinx.coroutines/issues/1204
    // TODO: Replace with runBlockingTest once issue is resolved
    @Test
    fun saveTask_retrievesTask() = runTest {
        // GIVEN - A new task saved in the database.
        val newTask = Task("title", "description", false)
        tasksLocalDataSource.saveTask(newTask)

        // WHEN  - Task retrieved by ID.
        val result = tasksLocalDataSource.getTask(newTask.id)

        // THEN - Same task is returned.
        assertThat(result.succeeded, `is`(true))
        result as Result.Success
        assertThat(result.data.title, `is`("title"))
        assertThat(result.data.description, `is`("description"))
        assertThat(result.data.isCompleted, `is`(false))
    }

    @Test
    fun completeTask_retrievedTaskIsComplete() = runTest {
        // 1. Save a new active task in the local data source.
        val activeTask = Task("title", "description", false)
        tasksLocalDataSource.saveTask(activeTask)
        // 2. Mark it as complete.
        activeTask.isCompleted = true
        database.taskDao().updateTask(activeTask)
        // 3. Check that the task can be retrieved from the local data source and is complete.
        val loaded = database.taskDao().getTaskById(activeTask.id)
        assertThat<Task>(loaded as Task, CoreMatchers.notNullValue())
        assertThat(loaded.id, `is`(activeTask.id))
        assertThat(loaded.title, `is`(activeTask.title))
        assertThat(loaded.description, `is`(activeTask.description))
        assertThat(loaded.isCompleted, `is`(true))
    }
}