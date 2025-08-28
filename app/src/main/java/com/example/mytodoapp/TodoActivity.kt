package com.example.mytodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.mytodoapp.ui.theme.MyToDoAppTheme
import com.example.mytodoapp.views.NavGraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TodoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(), // Light scrim for dark icons
                Color.Transparent.toArgb(), // Dark scrim for light icons (when in dark theme)
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Color.Transparent.toArgb(), // Light scrim for dark icons
                Color.Transparent.toArgb(), // Dark scrim for light icons (when in dark theme)
            )
        )
        setContent {
            MyToDoAppTheme {
                NavGraph(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
