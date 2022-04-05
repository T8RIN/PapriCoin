package ru.tech.papricoin.presentation.coin_list

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.tech.papricoin.domain.model.Coin
import ru.tech.papricoin.domain.model.Overview
import ru.tech.papricoin.presentation.coin_list.components.CoinListItem
import ru.tech.papricoin.presentation.coin_list.components.SearchBar
import ru.tech.papricoin.presentation.coin_list.viewModel.CoinListViewModel
import ru.tech.papricoin.presentation.utils.Screen
import ru.tech.papricoin.presentation.utils.UIState

@ExperimentalMaterial3Api
@Composable
fun CoinListScreen(navController: NavController, viewModel: CoinListViewModel = hiltViewModel()) {

    BackHandler { viewModel.showDialog(true) }

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
                        Text("Coins")
                    },
                    scrollBehavior = scrollBehavior,
                    actions = {
                        IconButton(onClick = { viewModel.reload() }) {
                            Icon(Icons.Outlined.Refresh, null)
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(Screen.FavoriteCoinsScreen.route) }) {
                            Icon(Icons.Rounded.FavoriteBorder, null)
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
                                .calculateBottomPadding() + 100.dp
                        )
                    ) {
                        item {
                            Row {
                                val overview = viewModel.overviewState.value
                                if (overview is UIState.Success<Overview?>) {
                                    Card(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                        modifier = Modifier
                                            .height(128.dp)
                                            .weight(1f)
                                            .padding(start = 10.dp, end = 5.dp, bottom = 20.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text("Cryptocurrencies", textAlign = TextAlign.Center)
                                            Text(overview.data?.cryptocurrenciesNumber.toString())
                                        }
                                    }

                                    Card(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                        modifier = Modifier
                                            .height(128.dp)
                                            .weight(1f)
                                            .padding(start = 5.dp, end = 10.dp, bottom = 20.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text("Bitcoin dominance", textAlign = TextAlign.Center)
                                            Text("${overview.data?.dominance}%")
                                        }
                                    }
                                }
                            }
                        }
                        items(data.data.size) { index ->
                            CoinListItem(
                                modifier = Modifier.fillMaxWidth(),
                                coin = data.data[index],
                                onClick = {
                                    navController.navigate(Screen.CoinDetailsScreen.route + "/$it")
                                })
                        }
                    }
                    SearchBar(Modifier.align(Alignment.BottomCenter)) {
                        viewModel.filterFor(it)
                    }
                }
                is UIState.Loading<*> -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }

    if (viewModel.dialogState.value) {
        val ctx = LocalContext.current as ComponentActivity
        AlertDialog(
            onDismissRequest = { viewModel.showDialog(false) },
            confirmButton = {
                TextButton(onClick = { viewModel.showDialog(false) }) {
                    Text("Stay")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    viewModel.showDialog(false)
                    ctx.finishAffinity()
                }) {
                    Text("Close")
                }
            },
            title = {
                Text("App closing")
            },
            text = {
                Text(
                    "Are you really want to close PapriCoin application?",
                    textAlign = TextAlign.Center
                )
            },
            icon = {
                Icon(Icons.Outlined.ExitToApp, null)
            }
        )
    }

}