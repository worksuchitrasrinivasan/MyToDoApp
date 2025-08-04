package com.example.mytodoapp.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.mytodoapp.R
import com.example.mytodoapp.views.Navigation.DATA_VIEW
import com.example.mytodoapp.views.Navigation.EDIT_VIEW

@Composable
fun FloatingActionButton(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            when(navController.currentDestination?.route) {
                DATA_VIEW -> {
                    navController.navigate(EDIT_VIEW)
                }
                EDIT_VIEW -> {
                    //save current task and go back
                    navController.popBackStack()
                }
            }
        },
        containerColor = colorResource(R.color.colorAccent)
    ) {
        when(navController.currentDestination?.route) {
            DATA_VIEW -> {
                Icon(Icons.Filled.Add, "Add", tint = colorResource(R.color.white))
            }
            EDIT_VIEW -> {
                //save current task and go back
                Icon(Icons.Filled.Done, "Done", tint = colorResource(R.color.white))
            }
        }

    }
}