package com.example.mytodoapp.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.mytodoapp.R
import com.example.mytodoapp.views.Navigation.DATA_VIEW
import com.example.mytodoapp.views.Navigation.EDIT_VIEW
import com.example.mytodoapp.views.Navigation.NO_DATA_VIEW

@Composable
fun FloatingActionButton(route: String?, navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            when(route) {
                NO_DATA_VIEW, DATA_VIEW -> {
                    navController.navigate(EDIT_VIEW)
                }
                EDIT_VIEW -> {
                    //save current task and go back
                    navController.navigate(DATA_VIEW)
                }
                else -> {}
            }
        },
        containerColor = colorResource(R.color.colorAccent)
    ) {
        if (route == EDIT_VIEW) {
            Icon(Icons.Filled.Done, "Done", tint = colorResource(R.color.white))
        } else {
            Icon(Icons.Filled.Add, "Add", tint = colorResource(R.color.white))
        }
    }
}