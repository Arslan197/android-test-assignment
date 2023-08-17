package com.example.shacklehotelbuddy.domain.model.request_params

data class PropertiesListingRequestParams(
    val checkInDate: CheckInDate,
    val checkOutDate: CheckOutDate,
    val currency: String = "USD",
    val destination: Destination = Destination("6054439"),
    val eapid: Int = 1,
    val filters: Filters = Filters(Price(min = 100, max = 150)),
    val locale: String = "en_US",
    val resultsSize: Int = 200,
    val resultsStartingIndex: Int = 0,
    val rooms: List<Room>,
    val siteId: Int = 300000001,
    val sort: String ="PRICE_LOW_TO_HIGH"
)