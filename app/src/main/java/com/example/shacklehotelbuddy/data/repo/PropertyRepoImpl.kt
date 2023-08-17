package com.example.shacklehotelbuddy.data.repo

import com.example.shacklehotelbuddy.data.local.dao.SearchHistoryDao
import com.example.shacklehotelbuddy.data.local.entities.SearchHistory
import com.example.shacklehotelbuddy.data.remote.RapidApiService
import com.example.shacklehotelbuddy.data.remote.dto.hotel_list_dto.PropertyListDTO
import com.example.shacklehotelbuddy.domain.model.request_params.PropertiesListingRequestParams
import com.example.shacklehotelbuddy.domain.repo.PropertyRepo
import javax.inject.Inject

class PropertyRepoImpl @Inject constructor(
    private val rapidApiService: RapidApiService,
    private val searchHistoryDao: SearchHistoryDao
) : PropertyRepo {
    override suspend fun getProperty(propertiesListingRequestParams: PropertiesListingRequestParams): PropertyListDTO {
        return rapidApiService.getPropertiesList(propertiesListingRequestParams)
    }

    override suspend fun insertSearchHistory(searchHistory: SearchHistory) {
        searchHistoryDao.insertSearchQuery(searchHistory    )
    }

    override suspend fun getSearchHistory(): List<SearchHistory> {
        return searchHistoryDao.getSearchQuery()
    }
}