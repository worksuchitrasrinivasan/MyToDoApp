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


object Navigation {

    const val DATA_VIEW = "data_view"
    const val NO_DATA_VIEW = "no_data_view"
    const val EDIT_VIEW = "edit_view"

    const val ADD_VIEW = "add_view"

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    fun Navigation(
        innerPadding: PaddingValues,
        navController: NavHostController,
        setFabAction: (()->Unit) -> Unit,
        todoViewModel: TodoViewModel = hiltViewModel()
    ) {

        val scope = rememberCoroutineScope()

        NavHost(navController = navController, startDestination = DATA_VIEW) {

            composable(NO_DATA_VIEW) {
                NoDataView(innerPadding, navController, setFabAction)
            }

            composable(DATA_VIEW) {
                DataView(innerPadding, navController, setFabAction)
            }

            composable(ADD_VIEW) {
                AddView(innerPadding, navController, setFabAction) { newTask ->
                    scope.launch {
                        todoViewModel.insertTask(newTask)
                    }
                }
            }

            composable(
                "$EDIT_VIEW/{task}",
                arguments = listOf(
                    navArgument("task") { type = TaskNavType}
                )
            ) { backStackEntry ->
                val task = backStackEntry.arguments?.getParcelable("task", TaskDTO::class.java)
                EditView(innerPadding, navController, setFabAction, task) { newTask ->
                    scope.launch {
                        newTask?.let {
                            todoViewModel.updateTask(it)
                        }
                    }
                }
            }
        }
    }

    object TaskNavType: NavType<TaskDTO>(isNullableAllowed = false) {

        override fun put( bundle: SavedState, key: String, value: TaskDTO) {
            return bundle.putParcelable(key, value)
        }

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun get(bundle: SavedState, key: String): TaskDTO? {
            return bundle.getParcelable(key, TaskDTO::class.java)
        }

        override fun parseValue(value: String): TaskDTO {
            return Gson().fromJson(value, TaskDTO::class.java)
        }

    }

}
