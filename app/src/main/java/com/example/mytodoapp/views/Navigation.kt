package com.example.mytodoapp.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation(innerPadding: PaddingValues) {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "no_data_view") {
        composable("no_data_view") {
            NoDataView(innerPadding)
        }
    }
}