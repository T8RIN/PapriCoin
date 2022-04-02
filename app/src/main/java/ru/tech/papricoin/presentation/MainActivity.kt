package ru.tech.papricoin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.tech.papricoin.R
import ru.tech.papricoin.presentation.app.PapriCoinApp
import ru.tech.papricoin.presentation.utils.rememberWindowSizeClass

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PapriCoin)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val windowSize = rememberWindowSizeClass()

            PapriCoinApp(windowSize = windowSize)
        }
    }
}