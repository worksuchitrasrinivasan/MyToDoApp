package com.example.mytodoapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import com.example.compose.MyToDoAppTheme
import com.example.mytodoapp.R
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.model.Task
import com.example.mytodoapp.viewmodel.TaskUiEvent
import com.example.mytodoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


@Composable
fun DataView(backStack: NavBackStack, viewModel: TodoViewModel = hiltViewModel()) {

    val taskUiState by viewModel.taskUiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Timber.d("taskUiState: $taskUiState")

    LaunchedEffect(Unit) {
        Timber.d("DataView: LaunchedEffect")
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
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { innerPadding ->

        // 3. Use the state to conditionally display the view
        if (showDataView || taskUiState.tasks.isNotEmpty()) {
            DataView(
                drawerState = if(showDataView)DrawerValue.Open else DrawerValue.Closed,
                innerPadding = innerPadding,
                tasks = taskUiState.tasks,
                edit = { it -> backStack.add(Screen.EditScreen(it)) },
                delete = { backStack.add(Screen.DeleteScreen) },
                update =  { newTask ->
                    viewModel.onEvent(TaskUiEvent.MarkTaskDone(newTask))

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
fun DataView(drawerState: DrawerValue = DrawerValue.Closed,
             innerPadding: PaddingValues,
             tasks: List<TaskDTO>,
             edit: (task: TaskDTO) -> Unit,
             delete: () -> Unit,
             update: (task: Task) -> Unit) {
    ModalNavigationDrawer(
        drawerState = DrawerState(drawerState),
        drawerContent = { TopAppBarDrawer() }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                text = stringResource(R.string.all_tasks),
                fontSize = 20.sp
            )
            LazyColumn(
                modifier = Modifier.padding(start = 25.dp)
            ) {
                items(items=tasks, key = { it.id }) { task ->
                    TaskItem(task, edit, delete, update)
                }
            }
        }
    }
}


@Composable
@Preview
fun DataViewPreview() {
    val tasks = listOf(TaskDTO(0, "hello task", "", false))
    ModalNavigationDrawer(
        drawerState = DrawerState(DrawerValue.Closed),
        drawerContent = { TopAppBarDrawer() }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()) {
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                text = stringResource(R.string.all_tasks),
                fontSize = 25.sp
            )
            LazyColumn(
                modifier = Modifier.padding(start = 25.dp)
            ) {
                items(items=tasks, key = { it.id }) { task ->
                    TaskItem(task, {}, {}, {})
                }
            }
        }
    }
}

@Composable
fun NoDataView(innerPadding: PaddingValues) {
    MyToDoAppTheme {
        Box(modifier = Modifier
            .padding(innerPadding)
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image( modifier = Modifier.size(80.dp), painter = painterResource(R.drawable.logo_no_fill), contentDescription = stringResource(R.string.no_task))
                Text(text = stringResource(R.string.you_have_no_tasks))
            }
        }
    }

}