package com.example.shacklehotelbuddy.domain.use_cases.search_history

import com.example.shacklehotelbuddy.data.local.entities.SearchHistory
import com.example.shacklehotelbuddy.domain.repo.PropertyRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchHistoryUseCase @Inject constructor(
    private val propertyRepo: PropertyRepo
) {

    operator fun invoke(): Flow<List<SearchHistory>> = flow {
        emit(
            propertyRepo.getSearchHistory()
        )
    }
}