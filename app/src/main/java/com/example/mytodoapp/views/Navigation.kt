package com.example.mytodoapp.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object Navigation {

    const val DATA_VIEW = "data_view"
    const val NO_DATA_VIEW = "no_data_view"
    const val EDIT_VIEW = "edit_view"

    @Composable
    fun Navigation(innerPadding: PaddingValues, navController: NavHostController) {
        NavHost(navController = navController, startDestination = NO_DATA_VIEW) {
            composable(NO_DATA_VIEW) {
                NoDataView(innerPadding)
            }

            composable(DATA_VIEW) {
                DataView(innerPadding)
            }

            composable(EDIT_VIEW) {
                EditView(innerPadding)
            }

        }
    }

}
