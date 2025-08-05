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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytodoapp.R
import com.example.mytodoapp.viewmodel.TodoViewModel
import timber.log.Timber


@Composable
fun DataView(navController: NavController, viewModel: TodoViewModel = hiltViewModel()) {

    val tasksState by viewModel.tasksFlow.collectAsState()


    Timber.d( "DataView: $tasksState")

    LaunchedEffect(Unit) {
        Timber.d("DataView: LaunchedEffect")
        viewModel.getAllTasks()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarView() },
        floatingActionButton = { FloatingActionButton(DATA_VIEW) {
            navController.navigate(ADD_VIEW)
        } },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Text(text = stringResource(R.string.all_tasks))
            Spacer(Modifier.size(30.dp))
            LazyColumn {
                items(items=tasksState, key = { it.id }) { task ->
                    TaskItem(task) { newTask ->
                        viewModel.updateTask(newTask)
                        viewModel.getAllTasks()
                    }
                }
            }
        }
    }



}