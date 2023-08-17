package com.example.shacklehotelbuddy.domain.model.property_list

data class Property(
    val id: String,
    val name: String?,
    val propertyImage: String?,
    val priceString: String?,
    val locationName: String?,
    val star: String?
)
