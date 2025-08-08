package com.example.mytodoapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import com.example.mytodoapp.R
import com.example.mytodoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


@Composable
fun DataView(backStack: NavBackStack, viewModel: TodoViewModel = hiltViewModel()) {

    val tasksState by viewModel.tasksFlow.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
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
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { innerPadding ->
        if(tasksState.isNotEmpty()) {
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

                Text(text = stringResource(R.string.all_tasks))
                Spacer(Modifier.size(30.dp))
                LazyColumn {
                    items(items=tasksState, key = { it.id }) { task ->
                        TaskItem(task, { backStack.add(Screen.EditScreen(it)) }, { backStack.add(Screen.DeleteScreen) }) { newTask ->

                            viewModel.updateTask(newTask)
                            viewModel.getAllTasks()

                            // show SnackBar
                            if(newTask.isDone) {
                                scope.launch {
                                    snackBarHostState.showSnackbar(message = "Task is marked complete")
                                }
                            }
                        }
                    }
                }
            }
        } else {
            NoDataView(innerPadding)
        }

    }

}

@Composable
fun NoDataView(innerPadding: PaddingValues) {
    Box(modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image( modifier = Modifier.size(80.dp), painter = painterResource(R.drawable.logo_no_fill), contentDescription = stringResource(R.string.no_task))
            Text(text = stringResource(R.string.you_have_no_tasks))
        }
    }
}