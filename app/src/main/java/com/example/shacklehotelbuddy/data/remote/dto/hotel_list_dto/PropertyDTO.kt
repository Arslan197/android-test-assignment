package com.example.shacklehotelbuddy.data.remote.dto.hotel_list_dto

import com.example.shacklehotelbuddy.domain.model.property_list.Property

data class PropertyDTO(
    val __typename: String,
    val availability: Availability,
    val destinationInfo: DestinationInfo,
    val featuredMessages: List<Any>,
    val id: String,
    val legalDisclaimer: Any,
    val listingFooter: Any,
    val mapMarker: MapMarker,
    val name: String,
    val neighborhood: Neighborhood,
    val offerBadge: OfferBadge,
    val offerSummary: OfferSummary,
    val pinnedDetails: Any,
    val price: Price,
    val priceAfterLoyaltyPointsApplied: PriceAfterLoyaltyPointsApplied,
    val priceMetadata: PriceMetadata,
    val propertyFees: List<Any>,
    val propertyImage: PropertyImage,
    val regionId: String,
    val reviews: Reviews,
    val saveTripItem: Any,
    val sponsoredListing: Any,
    val star: Any?,
    val supportingMessages: Any
)

fun PropertyDTO.toProperty(): Property {
    return Property(
        id = id,
        name = name,
        propertyImage = propertyImage.image.url,
        priceString = "${price.lead.formatted} ${price.priceMessages[0].value}",
        locationName = neighborhood.name,
        star = star?.let {
            toString()
        }
    )
}

