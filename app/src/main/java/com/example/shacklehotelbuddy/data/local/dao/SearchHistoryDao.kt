package com.example.shacklehotelbuddy.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shacklehotelbuddy.data.local.entities.SearchHistory

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM searchhistory ORDER BY searchDate DESC")
    suspend fun getSearchQuery(): List<SearchHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchQuery(entity: SearchHistory)

    @Query("DELETE from searchhistory where id = :id")
    suspend fun deleteSearchHistoryByID(id: Int)

    @Delete
    suspend fun deleteSearchQuery(entity: SearchHistory)

}