package ru.tech.papricoin.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.tech.papricoin.presentation.coin_detail.CoinDetailsScreen
import ru.tech.papricoin.presentation.coin_list.CoinListScreen
import ru.tech.papricoin.presentation.favorite_coins.FavoriteCoinsScreen
import ru.tech.papricoin.presentation.ui.theme.PapriCoinTheme
import ru.tech.papricoin.presentation.ui.utils.Screen

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun PapriCoinApp() {

    PapriCoinTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.CoinListScreen.route
        ) {
            composable(Screen.CoinListScreen.route) {
                CoinListScreen(navController)
            }
            composable(Screen.CoinDetailsScreen.route + "/{id}") {
                CoinDetailsScreen(navController)
            }
            composable(Screen.FavoriteCoinsScreen.route) {
                FavoriteCoinsScreen(navController)
            }
        }
    }

}