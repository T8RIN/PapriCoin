package ru.tech.papricoin.presentation.favorite_coins

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.tech.papricoin.domain.model.Coin
import ru.tech.papricoin.presentation.coin_list.components.CoinListItem
import ru.tech.papricoin.presentation.favorite_coins.viewModel.FavoriteCoinsViewModel
import ru.tech.papricoin.presentation.utils.Screen
import ru.tech.papricoin.presentation.utils.UIState

@ExperimentalMaterial3Api
@Composable
fun FavoriteCoinsScreen(
    navController: NavController,
    viewModel: FavoriteCoinsViewModel = hiltViewModel()
) {

    val scrollBehavior = viewModel.scrollBehavior.value
    Scaffold(
        topBar = {
            val backgroundColors = TopAppBarDefaults.smallTopAppBarColors()
            val backgroundColor = backgroundColors.containerColor(
                scrollFraction = scrollBehavior.scrollFraction
            ).value
            val foregroundColors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent
            )

            Surface(color = backgroundColor) {
                CenterAlignedTopAppBar(
                    modifier = Modifier.statusBarsPadding(),
                    colors = foregroundColors,
                    title = {
                        Text("Favourites")
                    },
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Rounded.ArrowBack, null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { viewModel.reload() }) {
                            Icon(Icons.Outlined.Refresh, null)
                        }
                    }
                )
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        Box(Modifier.fillMaxSize()) {
            when (val data = viewModel.coinListState.value) {
                is UIState.Empty<*> -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Something went wrong\n\n${data.message ?: ""}",
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(36.dp))
                        Button(onClick = { viewModel.reload() }) {
                            Icon(Icons.Outlined.Refresh, null)
                            Spacer(Modifier.width(24.dp))
                            Text("Try again")
                        }
                    }
                }
                is UIState.Success<List<Coin>> -> {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            top = 10.dp,
                            bottom = WindowInsets.navigationBars.asPaddingValues()
                                .calculateTopPadding() + 100.dp
                        )
                    ) {
                        items(data.data.size) { index ->
                            CoinListItem(
                                modifier = Modifier.fillMaxWidth(),
                                coin = data.data[index],
                                onClick = {
                                    navController.navigate(Screen.CoinDetailsScreen.route + "/$it")
                                })
                        }
                    }
                }
                is UIState.Loading<*> -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }

}