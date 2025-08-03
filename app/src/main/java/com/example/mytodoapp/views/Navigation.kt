package com.example.mytodoapp.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Navigation(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(navController = navController, startDestination = "no_data_view") {
        composable("no_data_view") {
            NoDataView(innerPadding)
        }
    }
}