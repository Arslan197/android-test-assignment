package com.example.shacklehotelbuddy.domain.model.request_params

data class Room(
    val adults: Int,
    val children: List<Children>
)