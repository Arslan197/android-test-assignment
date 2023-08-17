package com.example.shacklehotelbuddy.data.remote.dto.hotel_list_dto

data class Availability(
    val __typename: String,
    val available: Boolean,
    val minRoomsLeft: Int
)