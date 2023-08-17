package com.example.shacklehotelbuddy.data.remote.dto.hotel_list_dto

data class PropertyImage(
    val __typename: String,
    val alt: String,
    val fallbackImage: Any,
    val image: Image,
    val subjectId: Int
)