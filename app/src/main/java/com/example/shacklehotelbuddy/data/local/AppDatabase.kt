package com.example.shacklehotelbuddy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shacklehotelbuddy.data.local.dao.SearchHistoryDao
import com.example.shacklehotelbuddy.data.local.entities.SearchHistory

@Database(entities = [SearchHistory::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {
        const val DATABASE_NAME = "shackle_db"
    }
}