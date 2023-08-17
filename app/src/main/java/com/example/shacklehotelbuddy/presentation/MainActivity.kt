package com.example.shacklehotelbuddy.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shacklehotelbuddy.data.local.entities.SearchHistory
import com.example.shacklehotelbuddy.domain.utils.DataMappers.mapSearchParamsToRequestParams
import com.example.shacklehotelbuddy.presentation.search_hotel.SearchScreen
import com.example.shacklehotelbuddy.presentation.search_result.SearchResultScreen
import com.example.shacklehotelbuddy.presentation.ui.theme.ShackleHotelBuddyTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShackleHotelBuddyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.SearchScreen.route
                ) {

                    composable(route = Screens.SearchScreen.route) {
                        val propertyViewModel: PropertyViewModel = hiltViewModel()
                        val searchHistoryList: List<SearchHistory> =
                            propertyViewModel.searchHistoryList.collectAsState(
                                emptyList()
                            ).value
                        SearchScreen(
                            navController = navController,
                            searchHistoryList = searchHistoryList
                        ) {
                            propertyViewModel.insertSearchHistory(it)
                            navController.currentBackStackEntry?.savedStateHandle?.apply {
                                set("searchQuery", Gson().toJson(it))
                            }
                            navController.navigate(Screens.SearchResultScreen.route)
                        }

                    }

                    composable(
                        route = Screens.SearchResultScreen.route
                    ) {
                        val propertyViewModel: PropertyViewModel = hiltViewModel()
                        val searchQueryJson =
                            navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                                "searchQuery"
                            )

                        val searchQuery = Gson().fromJson(searchQueryJson, SearchHistory::class.java)
                        searchQuery?.let {
                            propertyViewModel.getProperties(
                                mapSearchParamsToRequestParams(searchQuery)
                            )
                        }

                        val propertyList = propertyViewModel.searchResultState.collectAsState()
                        val isLoading = propertyViewModel.isLoading.collectAsState()
                        SearchResultScreen(
                            navController = navController,
                            propertyList,
                            isLoading
                        )
                    }

                }
            }
        }
    }
}