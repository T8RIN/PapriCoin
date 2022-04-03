package ru.tech.papricoin.presentation.coin_detail.components

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.math.RoundingMode
import java.text.DecimalFormat

@ExperimentalMaterial3Api
@Composable
fun Chart(lineChartData: List<Double>, color: Color) {
    val maxValue = lineChartData.maxOrNull() ?: 0.toDouble()
    val minValue = lineChartData.minOrNull() ?: 0.toDouble()
    val currentValue = lineChartData.last()

    val df = DecimalFormat("#.##").apply {
        roundingMode = RoundingMode.CEILING
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .wrapContentSize(align = Alignment.BottomStart)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                val distance = size.width / (lineChartData.size + 1)
                var currentX = 0F
                val points = mutableListOf<PointF>()

                lineChartData.forEachIndexed { index, data ->
                    if (lineChartData.size >= index + 2) {
                        val y0 = (maxValue - data) * (size.height / maxValue)
                        val x0 = currentX + distance
                        points.add(PointF(x0, y0.toFloat()))
                        currentX += distance
                    }
                }

                for (i in 0 until points.size - 1) {
                    drawLine(
                        start = Offset(points[i].x, points[i].y),
                        end = Offset(points[i + 1].x, points[i + 1].y),
                        color = color,
                        strokeWidth = 2f
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text("Min: ${df.format(minValue)}$")
                    Spacer(Modifier.height(4.dp))
                    Text("Max: ${df.format(maxValue)}$")
                }
                Spacer(Modifier.weight(1f))
                Text("Current: ${df.format(currentValue)}$")
            }
        }
    }
}