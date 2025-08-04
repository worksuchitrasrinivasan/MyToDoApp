package com.example.mytodoapp.views

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.mytodoapp.TodoApp.Companion.TAG
import com.example.mytodoapp.viewmodel.TodoViewModel
import com.example.mytodoapp.views.Navigation.ADD_VIEW


@Composable
fun DataView(innerPadding: PaddingValues, navController: NavController,
             setFabAction: (()->Unit) -> Unit, viewModel: TodoViewModel = hiltViewModel()) {

    val tasksState by viewModel.tasksFlow.collectAsState()
    viewModel.getAllTasks()

    Log.d(TAG, "DataView: $tasksState")

    LaunchedEffect(Unit) {
        setFabAction {
            navController.navigate(ADD_VIEW)
        }
    }

//    val tasksState = listOf(TaskDTO(1, "hello task", "test desc", false))

    Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        Text(text = stringResource(R.string.all_tasks))
        Spacer(Modifier.size(30.dp))
        LazyColumn {
            items(items=tasksState) { task ->
                TaskItem(task)
            }
        }
    }

}