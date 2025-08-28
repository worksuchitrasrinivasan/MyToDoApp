package com.example.mytodoapp.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.mytodoapp.viewmodel.TaskUiEvent
import com.example.mytodoapp.viewmodel.TodoViewModel


@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val backstack = rememberNavBackStack(Screen.DataScreen)
    val viewModel: TodoViewModel = hiltViewModel()

    NavDisplay(
        backStack = backstack,
        modifier = modifier,
        onBack = { backstack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Screen.DataScreen> {
                DataView(backstack)
            }

            entry<Screen.AddScreen> {
                EditView(backstack, null){ task ->
                    viewModel.onEvent(TaskUiEvent.AddTask(task))
                }
            }

            entry<Screen.DeleteScreen> {
                DeleteView(backstack)
            }

            entry<Screen.EditScreen> { key ->
                EditView(backstack, key.task) { task ->
                    viewModel.onEvent(TaskUiEvent.UpdateTask(task))
                }
            }

        }
    )

}



