package com.example.mytodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mytodoapp.ui.theme.MyToDoAppTheme
import com.example.mytodoapp.views.NavGraph
import com.example.mytodoapp.views.TopAppBarView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TodoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { padding ->
                MyToDoAppTheme {
                    NavGraph(modifier = Modifier.padding(padding).fillMaxSize())
                }
            }

        }
    }
}
