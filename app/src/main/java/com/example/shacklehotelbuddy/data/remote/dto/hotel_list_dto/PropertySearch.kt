package com.example.shacklehotelbuddy.data.remote.dto.hotel_list_dto

data class PropertySearch(
    val __typename: String,
    val clickstream: Clickstream,
    val filterMetadata: FilterMetadata,
    val map: Map,
    val properties: List<PropertyDTO>,
    val propertySearchListings: List<Any>,
    val searchCriteria: SearchCriteria,
    val shoppingContext: ShoppingContext,
    val summary: Summary,
    val universalSortAndFilter: UniversalSortAndFilter
)