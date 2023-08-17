package com.example.shacklehotelbuddy.data.remote

import com.example.shacklehotelbuddy.data.remote.dto.hotel_list_dto.PropertyListDTO
import com.example.shacklehotelbuddy.domain.model.request_params.PropertiesListingRequestParams
import retrofit2.http.Body
import retrofit2.http.POST

interface RapidApiService {

    @POST("properties/v2/list")
    suspend fun getPropertiesList(
        @Body propertiesListSearchEntity: PropertiesListingRequestParams
    ): PropertyListDTO

}