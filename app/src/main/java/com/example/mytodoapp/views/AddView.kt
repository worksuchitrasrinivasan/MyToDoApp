package com.example.mytodoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import com.example.mytodoapp.R
import com.example.mytodoapp.model.Task
import kotlinx.coroutines.launch


@Composable
fun AddView( backStack: NavBackStack, save: (task: Task) -> Unit) {

    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarView() },
        floatingActionButton = { FloatingActionButton(Screen.AddScreen) {
            if(title.isNotEmpty()) {
                save(Task(0, title, description, false))
                backStack.removeLastOrNull()
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar("Task with empty title can't be added")
                }
            }
        } },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            TextField(title, onValueChange = { it ->
                title = it
            }, placeholder = { Text(stringResource(R.string.title)) }, label = { Text(stringResource(R.string.task_title)) })

            Spacer(Modifier.size(40.dp))

            TextField(value = description, onValueChange = {
                    it -> description = it
            }, placeholder = { Text(stringResource(R.string.description)) }, label = { Text(stringResource(R.string.task_description)) })
        }
    }

}