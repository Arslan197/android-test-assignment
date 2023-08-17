package com.example.shacklehotelbuddy.data.remote.dto.hotel_list_dto

data class Price(
    val __typename: String,
    val displayMessages: List<DisplayMessage>,
    val lead: Lead,
    val options: List<Option>,
    val priceMessages: List<PriceMessage>,
    val priceMessaging: Any,
    val strikeOut: Any,
    val strikeOutType: String
)