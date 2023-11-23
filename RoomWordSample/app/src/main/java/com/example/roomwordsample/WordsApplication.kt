package com.example.roomwordsample

import android.app.Application
import com.example.roomwordsample.data.WordRoomDatabase
import com.example.roomwordsample.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * Created a database instance.
 * Created a repository instance, based on the database DAO.
 * Because these objects should only be created when they're first needed,
 * rather than at app startup, you're using Kotlin's property delegation: by lazy.
 */
class WordsApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}