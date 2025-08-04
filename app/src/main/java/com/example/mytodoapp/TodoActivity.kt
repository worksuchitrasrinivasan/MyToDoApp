package com.example.mytodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mytodoapp.ui.theme.MyToDoAppTheme
import com.example.mytodoapp.views.FloatingActionButton
import com.example.mytodoapp.views.Navigation.Navigation
import com.example.mytodoapp.views.TopAppBarView
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TodoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyToDoAppTheme {
                val navController: NavHostController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val routeString by rememberSaveable(currentRoute) { mutableStateOf(currentRoute) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBarView() },
                    floatingActionButton = { FloatingActionButton(routeString, navController) },
                    floatingActionButtonPosition =  FabPosition.End
                    ) { innerPadding ->
                    Navigation(innerPadding, navController)
                }
            }
        }
    }
}
