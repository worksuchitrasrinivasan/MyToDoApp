package com.example.mytodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.compose.MyToDoAppTheme
import com.example.mytodoapp.views.NavGraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TodoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyToDoAppTheme {
                NavGraph(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
