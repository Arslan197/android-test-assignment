package com.example.shacklehotelbuddy.presentation.search_result

import com.example.shacklehotelbuddy.domain.model.property_list.Property

data class SearchResultState(

    val isLoading: Boolean = false,
    val properties: List<Property> = emptyList(),
    val error: String = ""

)
