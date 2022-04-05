package ru.tech.papricoin.presentation.utils

sealed class Screen(val route: String) {
    object CoinListScreen : Screen(route = "coin_list")
    object CoinDetailsScreen : Screen(route = "coin_details")
    object FavoriteCoinsScreen : Screen(route = "favorite_coins")
}
