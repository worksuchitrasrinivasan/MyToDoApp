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
                AddView(backstack)
            }

            entry<Screen.DeleteScreen> {
                DeleteView(backstack)
            }

            entry<Screen.EditScreen> { key ->
                EditView(backstack, key.task) { task ->
                    viewModel.updateTask(task)
                }
            }

        }
    )

}



