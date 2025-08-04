package com.example.mytodoapp.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.savedstate.SavedState
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.viewmodel.TodoViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
object Navigation {

    const val DATA_VIEW = "data_view"
    const val NO_DATA_VIEW = "no_data_view"
    const val EDIT_VIEW = "edit_view"

    @Composable
    fun Navigation(innerPadding: PaddingValues, navController: NavHostController, todoViewModel: TodoViewModel = hiltViewModel()) {

        val scope = rememberCoroutineScope()

        NavHost(navController = navController, startDestination = DATA_VIEW) {

            composable(NO_DATA_VIEW) {
                NoDataView(innerPadding)
            }

            composable(DATA_VIEW) {
                DataView(innerPadding)
            }

            composable(
                EDIT_VIEW,
                arguments = listOf(
                    navArgument("task") { type = TaskNavType}
                )
            ) { backStackEntry ->
                val task = backStackEntry.arguments?.getParcelable("task", TaskDTO::class.java)
                EditView(innerPadding, task) { newTask ->
                    scope.launch {
                        if(task == null) {
                            todoViewModel.insertTask(newTask)
                        } else {
                            todoViewModel.updateTask(newTask)
                        }
                    }
                }
            }

        }
    }

    object TaskNavType: NavType<TaskDTO>(isNullableAllowed = true) {
        override fun put( bundle: SavedState, key: String, value: TaskDTO) {
            return bundle.putParcelable(key, value)
        }


        override fun get(bundle: SavedState, key: String): TaskDTO? {
            return bundle.getParcelable(key, TaskDTO::class.java)
        }

        override fun parseValue(value: String): TaskDTO {
            return Gson().fromJson(value, TaskDTO::class.java)
        }

    }

}
