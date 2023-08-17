package com.example.shacklehotelbuddy.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo
    val checkInDate: String?,
    @ColumnInfo
    val checkOutData: String?,
    @ColumnInfo
    val adults: Int,
    @ColumnInfo
    val children: Int,
    @ColumnInfo
    val searchDate: Long
)