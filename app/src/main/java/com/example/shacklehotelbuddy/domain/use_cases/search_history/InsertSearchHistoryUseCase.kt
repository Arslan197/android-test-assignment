package com.example.shacklehotelbuddy.domain.use_cases.search_history

import com.example.shacklehotelbuddy.data.local.entities.SearchHistory
import com.example.shacklehotelbuddy.domain.repo.PropertyRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertSearchHistoryUseCase @Inject constructor(
    private val propertyRepo: PropertyRepo
) {

    operator fun invoke(searchHistory: SearchHistory): Flow<Unit> = flow {
        emit(
            propertyRepo.insertSearchHistory(searchHistory)
        )
    }
}