package com.example.shacklehotelbuddy.domain.repo

import com.example.shacklehotelbuddy.data.local.entities.SearchHistory
import com.example.shacklehotelbuddy.data.remote.dto.hotel_list_dto.PropertyListDTO
import com.example.shacklehotelbuddy.domain.model.request_params.PropertiesListingRequestParams

interface PropertyRepo {

    suspend fun getProperty(propertiesListingRequestParams: PropertiesListingRequestParams): PropertyListDTO

    suspend fun insertSearchHistory(searchHistory: SearchHistory)

    suspend fun getSearchHistory(): List<SearchHistory>
}