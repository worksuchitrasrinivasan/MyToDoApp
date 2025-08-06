package com.example.mytodoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import com.example.mytodoapp.R
import com.example.mytodoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


@Composable
fun DataView(backStack: NavBackStack, viewModel: TodoViewModel = hiltViewModel()) {

    val tasksState by viewModel.tasksFlow.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Timber.d( "DataView: $tasksState")

    LaunchedEffect(Unit) {
        Timber.d("DataView: LaunchedEffect")
        viewModel.getAllTasks()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarView() },
        floatingActionButton = { FloatingActionButton(Screen.DataScreen) {
            backStack.add(Screen.AddScreen)
        } },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Text(text = stringResource(R.string.all_tasks))
            Spacer(Modifier.size(30.dp))
            LazyColumn {
                items(items=tasksState, key = { it.id }) { task ->
                    TaskItem(task) { newTask ->

                        viewModel.updateTask(newTask)
                        viewModel.getAllTasks()

                        // show SnackBar
                        if(newTask.isDone) {
                            scope.launch {
                                snackbarHostState.showSnackbar(message = "Task is marked complete")
                            }
                        }
                    }
                }
            }
        }
    }



}