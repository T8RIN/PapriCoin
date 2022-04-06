package ru.tech.papricoin.presentation.coin_detail.components

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import java.math.RoundingMode
import java.text.DecimalFormat

@ExperimentalMaterial3Api
@Composable
fun Chart(lineChartData: List<Double>, color: Color) {
    val maxValue = lineChartData.maxOrNull() ?: 0.toDouble()
    val minValue = lineChartData.minOrNull() ?: 0.toDouble()
    val currentValue = lineChartData.lastOrNull() ?: 0.toDouble()

    val df = DecimalFormat("#.##").apply {
        roundingMode = RoundingMode.CEILING
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .wrapContentSize(align = Alignment.BottomStart)
        ) {
            Text(
                text = "* last year statistic",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 7.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )

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

                val clipPath = Path().apply {
                    lineTo(points.first().x, size.height)
                    lineTo(points.first().x, points.last().y)
                }

                for (i in 0 until points.size - 1) {
                    drawLine(
                        start = Offset(points[i].x, points[i].y),
                        end = Offset(points[i + 1].x, points[i + 1].y),
                        color = color,
                    )
                    clipPath.lineTo(points[i].x, points[i].y)
                }

                clipPath.apply {
                    lineTo(points.last().x, points.last().y)
                    lineTo(points.last().x, size.height)
                    lineTo(points.first().x, size.height)
                }

                clipPath(clipPath) {
                    drawRect(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(
                                    ColorUtils.blendARGB(
                                        color.toArgb(),
                                        Color.Gray.toArgb(),
                                        0.6f
                                    )
                                ),
                                Color.Transparent
                            )
                        )
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

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