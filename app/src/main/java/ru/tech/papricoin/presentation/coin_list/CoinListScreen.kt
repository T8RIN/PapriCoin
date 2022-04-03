package ru.tech.papricoin.presentation.coin_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Box(Modifier.fillMaxSize()) {
        when (val data = viewModel.coinListState.value) {
            is UIState.Empty<*> -> {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(10.dp),
                    text = "Something went wrong\n${data.message ?: ""}",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            is UIState.Success<List<Coin>> -> {
                LazyColumn(contentPadding = WindowInsets.systemBars.asPaddingValues()) {
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