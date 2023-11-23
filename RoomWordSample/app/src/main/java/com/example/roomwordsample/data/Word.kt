package com.example.roomwordsample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This class will describe the Entity (which represents the SQLite table) for your words.
 * Each property in the class represents a column in the table.
 * Room will ultimately use these properties to both create the table and instantiate objects from rows in the database.
 */
@Entity(tableName = "word_table")
class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String)
