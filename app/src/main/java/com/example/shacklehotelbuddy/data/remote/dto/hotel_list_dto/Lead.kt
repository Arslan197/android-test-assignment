package com.example.shacklehotelbuddy.data.remote.dto.hotel_list_dto

data class Lead(
    val __typename: String,
    val amount: Double,
    val currencyInfo: CurrencyInfo,
    val formatted: String
)