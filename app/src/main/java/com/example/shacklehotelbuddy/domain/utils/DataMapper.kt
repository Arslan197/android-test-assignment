package com.example.shacklehotelbuddy.domain.utils

import com.example.shacklehotelbuddy.data.local.entities.SearchHistory
import com.example.shacklehotelbuddy.domain.model.request_params.CheckInDate
import com.example.shacklehotelbuddy.domain.model.request_params.CheckOutDate
import com.example.shacklehotelbuddy.domain.model.request_params.Children
import com.example.shacklehotelbuddy.domain.model.request_params.PropertiesListingRequestParams
import com.example.shacklehotelbuddy.domain.model.request_params.Room

object DataMappers {

    fun mapSearchParamsToRequestParams(searchHistory: SearchHistory): PropertiesListingRequestParams {
        val checkInDate = searchHistory.checkInDate?.let {
            it.split("/")
        }
        val checkOutDate = searchHistory.checkOutData?.let {
            it.split("/")
        }

        val children = mutableListOf<Children>()
        for (i in 1..searchHistory.children) {
            children.add(Children(5))
        }
        return PropertiesListingRequestParams(
            checkInDate = CheckInDate(
                checkInDate!![0].toInt(),
                checkInDate[1].toInt(),
                checkInDate[2].toInt()

            ),
            checkOutDate = CheckOutDate(
                checkOutDate!![0].toInt(),
                checkOutDate[1].toInt(),
                checkOutDate[2].toInt()
            ),
            rooms = listOf(Room(searchHistory.adults, children))
        )
    }
}