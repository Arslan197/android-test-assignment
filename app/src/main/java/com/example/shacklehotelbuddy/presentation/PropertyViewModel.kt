package com.example.shacklehotelbuddy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shacklehotelbuddy.common.Resource
import com.example.shacklehotelbuddy.data.local.entities.SearchHistory
import com.example.shacklehotelbuddy.domain.model.property_list.Property
import com.example.shacklehotelbuddy.domain.model.request_params.PropertiesListingRequestParams
import com.example.shacklehotelbuddy.domain.use_cases.search_history.GetSearchHistoryUseCase
import com.example.shacklehotelbuddy.domain.use_cases.search_history.InsertSearchHistoryUseCase
import com.example.shacklehotelbuddy.domain.use_cases.search_result.SearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    private val searchResultUseCase: SearchResultUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase
) : ViewModel() {


    private val _searchResultState = MutableStateFlow(emptyList<Property>())
    val searchResultState = _searchResultState

    private val _searchQueryList = MutableStateFlow(emptyList<SearchHistory>())
    val searchHistoryList get() = _searchQueryList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()


    init {
        getSearchHistory()
    }

    private fun getSearchHistory() {
        viewModelScope.launch {
            getSearchHistoryUseCase().collect { response ->
                _searchQueryList.value = response
            }
        }
    }

    fun insertSearchHistory(searchHistory: SearchHistory) {
        viewModelScope.launch(Dispatchers.IO) {
            insertSearchHistoryUseCase(searchHistory).collect {
                getSearchHistory()
            }
        }
    }

    fun getProperties(propertyListingRequestParams: PropertiesListingRequestParams) {
        searchResultUseCase(propertyListingRequestParams).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _isLoading.value = false
                    _searchResultState.value =
                        result.data!!
                }

                is Resource.Error -> {
                    _isLoading.value = false
                }

                is Resource.Loading -> {
                    _isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

}