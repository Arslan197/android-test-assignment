package com.example.shacklehotelbuddy.presentation

sealed class Screens(
    val route: String
) {
    object SearchScreen : Screens("search_screen")

    object SearchResultScreen : Screens("search_result_screen")
}