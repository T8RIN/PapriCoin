package ru.tech.papricoin.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import ru.tech.papricoin.domain.model.CoinDetail
import ru.tech.papricoin.presentation.coin_detail.components.Chart
import ru.tech.papricoin.presentation.coin_detail.components.Tag
import ru.tech.papricoin.presentation.coin_detail.components.TeamListItem
import ru.tech.papricoin.presentation.coin_detail.viewModel.CoinDetailsViewModel
import ru.tech.papricoin.presentation.utils.UIState

@ExperimentalMaterial3Api
@Composable
fun CoinDetailsScreen(
    viewModel: CoinDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.coinDetailState.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (state) {
            is UIState.Loading<*> -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            is UIState.Empty<*> -> {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(10.dp),
                    text = "Something went wrong\n${state.message ?: ""}",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            is UIState.Success<CoinDetail?> -> {
                state.data?.let { details ->
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = WindowInsets.systemBars.asPaddingValues()
                    ) {
                        item {
                            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 20.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${details.rank}. ${details.name} (${details.symbol})",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        modifier = Modifier.weight(8f)
                                    )
                                    Text(
                                        text = if (details.isActive) "active" else "inactive",
                                        color = Color(if (details.isActive) 0xFF1b6d0d else 0xFF9c4043),
                                        fontStyle = Italic,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier
                                            .align(CenterVertically)
                                            .weight(2f)
                                    )
                                }
                                Spacer(modifier = Modifier.height(15.dp))

                                when (val hState = viewModel.currencyHistoryState.value) {
                                    is UIState.Success -> {
                                        Chart(
                                            lineChartData = hState.data.map { it.currency },
                                            MaterialTheme.colorScheme.primary
                                        )
                                    }
                                    is UIState.Loading -> {
                                        Box(Modifier.fillMaxWidth()) {
                                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                                        }
                                    }
                                    is UIState.Empty -> {
                                        Text(
                                            hState.message ?: "",
                                            color = MaterialTheme.colorScheme.onBackground
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(15.dp))
                                Text(
                                    text = details.description,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(15.dp))
                                if (details.tags.isNotEmpty()) {
                                    Text(
                                        text = "Tags",
                                        color = MaterialTheme.colorScheme.onBackground,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    Spacer(modifier = Modifier.height(15.dp))
                                    FlowRow(
                                        mainAxisSpacing = 10.dp,
                                        crossAxisSpacing = 10.dp,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        details.tags.forEach { tag ->
                                            Tag(tag = tag)
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(15.dp))
                                }
                                if (details.team.isNotEmpty()) {
                                    Text(
                                        text = "Team members",
                                        color = MaterialTheme.colorScheme.onBackground,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    Spacer(modifier = Modifier.height(15.dp))
                                }
                            }
                        }
                        items(details.team.size) { index ->
                            TeamListItem(
                                teamMember = details.team[index],
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp, vertical = 5.dp)
                            )
                            Divider(modifier = Modifier.padding(horizontal = 20.dp))
                        }
                    }
                }

            }
        }
    }
}