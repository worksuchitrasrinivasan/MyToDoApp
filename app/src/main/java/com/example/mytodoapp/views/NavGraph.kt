package com.example.mytodoapp.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.mytodoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.launch


@Composable
fun NavGraph(modifier: Modifier = Modifier, todoViewModel: TodoViewModel = hiltViewModel()) {


    val backstack = rememberNavBackStack(Screen.DataScreen)
    val scope = rememberCoroutineScope()

    NavDisplay(
        backStack = backstack,
        onBack = { backstack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<Screen.DataScreen> {
                DataView(backstack)
            }

            entry<Screen.AddScreen> {
                AddView(backstack)
            }

            entry<Screen.DeleteScreen> { key ->
                DeleteView(backstack)
            }

            entry<Screen.EditScreen> { key ->
                EditView(backstack, key.task) { newTask ->
                    scope.launch {
                        newTask?.let {
                            todoViewModel.updateTask(it)
                        }
                    }
                }
            }

        }
    )

}



