package com.example.mytodoapp.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytodoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    fun NavGraph(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        scope: CoroutineScope = rememberCoroutineScope(),
        todoViewModel: TodoViewModel = hiltViewModel()
    ) {

        NavHost(navController = navController, startDestination = DATA_VIEW) {

            composable(NO_DATA_VIEW) {
                NoDataView( navController)
            }

            composable(DATA_VIEW) {
                DataView(navController)
            }

            composable(ADD_VIEW) {
                AddView(navController) { newTask ->
                    scope.launch {
                        todoViewModel.insertTask(newTask)
                    }
                }
            }

//            composable(
//                "$EDIT_VIEW/{task}",
//                arguments = listOf(
//                    navArgument("task") { type = TaskNavType}
//                )
//            ) { backStackEntry ->
//                val task = backStackEntry.arguments?.getParcelable("task", TaskDTO::class.java)
//                EditView(navController, task) { newTask ->
//                    scope.launch {
//                        newTask?.let {
//                            todoViewModel.updateTask(it)
//                        }
//                    }
//                }
//            }
        }
    }



