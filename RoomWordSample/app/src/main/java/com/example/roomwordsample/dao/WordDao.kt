package com.example.roomwordsample.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomwordsample.data.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    /**
     * Flow produces values one at a time (instead of all at once) that can generate values from async operations
     * like network requests, database calls, or other async code. It supports coroutines throughout its API,
     * so you can transform a flow using coroutines as well!
     */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}