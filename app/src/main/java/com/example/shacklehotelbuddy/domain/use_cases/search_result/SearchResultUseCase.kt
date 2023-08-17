package com.example.shacklehotelbuddy.domain.use_cases.search_result

import com.example.shacklehotelbuddy.common.Resource
import com.example.shacklehotelbuddy.data.remote.dto.hotel_list_dto.toProperty
import com.example.shacklehotelbuddy.domain.model.property_list.Property
import com.example.shacklehotelbuddy.domain.model.request_params.PropertiesListingRequestParams
import com.example.shacklehotelbuddy.domain.repo.PropertyRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchResultUseCase @Inject constructor(
    private val propertyRepo: PropertyRepo
) {

    operator fun invoke(propertiesListingRequestParams: PropertiesListingRequestParams): Flow<Resource<List<Property>>> =
        flow {
            try {
                emit(Resource.Loading())
                val propertyList =
                    propertyRepo.getProperty(propertiesListingRequestParams).data.propertySearch.properties.map {
                        it.toProperty()
                    }
                emit(Resource.Success(propertyList))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
}