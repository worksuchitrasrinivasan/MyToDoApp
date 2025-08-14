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
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import com.example.mytodoapp.R
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.model.Task
import com.example.mytodoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


@Composable
fun DataView(backStack: NavBackStack, viewModel: TodoViewModel = hiltViewModel()) {

    val tasksItemList by viewModel.tasksFlow.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Timber.d( "DataView: $tasksItemList")

    LaunchedEffect(Unit) {
        Timber.d("DataView: LaunchedEffect")
        viewModel.getAllTasks()
    }

    var showDataView by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // 2. Add an onClick lambda to the TopAppBar
        topBar = { TopAppBarView(onClick = { showDataView = !showDataView }) },
        floatingActionButton = {
            FloatingActionButton(Screen.DataScreen) {
                backStack.add(Screen.AddScreen)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { innerPadding ->

        // 3. Use the state to conditionally display the view
        if (showDataView || tasksItemList.isNotEmpty()) {
            DataView(
                drawerState = if(showDataView)DrawerValue.Open else DrawerValue.Closed,
                innerPadding = innerPadding,
                tasksState = tasksItemList,
                edit = { it -> backStack.add(Screen.EditScreen(it)) },
                delete = { backStack.add(Screen.DeleteScreen) },
                update =  { newTask ->
                    viewModel.updateTask(newTask)
                    viewModel.getAllTasks()

                    // show SnackBar
                    if(newTask.isDone) {
                        scope.launch {
                            snackBarHostState.showSnackbar(message = "Task is marked complete")
                        }
                    }
                }
            )
        } else {
            NoDataView(innerPadding)
        }

    }

}

@Composable
fun DataView(drawerState: DrawerValue = DrawerValue.Closed, innerPadding: PaddingValues, tasksState: List<TaskDTO>, edit: (task: TaskDTO) -> Unit, delete: () -> Unit, update: (task: Task) -> Unit) {
    ModalNavigationDrawer(
        drawerState = DrawerState(drawerState),
        drawerContent = { TopAppBarDrawer() }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Text(text = stringResource(R.string.all_tasks))
            Spacer(Modifier.size(30.dp))
            LazyColumn {
                items(items=tasksState, key = { it.id }) { task ->
                    TaskItem(task, edit, delete, update)
                }
            }
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