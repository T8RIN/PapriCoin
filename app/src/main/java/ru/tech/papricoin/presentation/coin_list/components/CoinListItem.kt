package ru.tech.papricoin.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoneyOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import ru.tech.papricoin.domain.model.Coin

@Composable
fun CoinListItem(modifier: Modifier = Modifier, coin: Coin, onClick: (String) -> Unit) {
    Box(
        modifier
            .background(MaterialTheme.colorScheme.background)
            .clickable { onClick(coin.id) }) {
        Column {
            Spacer(modifier = Modifier.size(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.size(10.dp))
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .size(48.dp),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(coin.iconUrl)
                        .crossfade(true)
                        .build(),
                    loading = {
                        Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    },
                    error = {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Outlined.MoneyOff,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    "${coin.rank}. ${coin.name} (${coin.symbol})",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Column {
                    if (coin.isNew) Text(
                        "NEW",
                        color = Color(0xFFe4c54a),
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                    )
                    Text(
                        if (coin.isActive) "active" else "inactive",
                        color = Color(if (coin.isActive) 0xFF1b6d0d else 0xFF9c4043),
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
            Spacer(modifier = Modifier.size(10.dp))
            Divider(Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.surfaceVariant)
        }
    }
}