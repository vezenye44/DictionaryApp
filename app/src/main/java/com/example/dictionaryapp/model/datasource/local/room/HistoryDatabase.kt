package com.example.dictionaryapp.model.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HistoryEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class HistoryDatabase: RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}