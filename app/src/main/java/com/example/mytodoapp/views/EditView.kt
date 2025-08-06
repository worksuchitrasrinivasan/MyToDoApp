package com.example.mytodoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import com.example.mytodoapp.R
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.dto.toTask
import com.example.mytodoapp.model.Task


@Composable
fun EditView( backStack: NavBackStack, task: TaskDTO?= null, save: (task: Task?) -> Unit) {

    val newTask = task ?: TaskDTO()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarView() },
        floatingActionButton = { FloatingActionButton(Screen.EditScreen) {
            save(newTask.toTask())
            backStack.removeLastOrNull()
        } },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            TextField(newTask.title, onValueChange = { it ->
                newTask.title = it
            }, placeholder = { Text(stringResource(R.string.title)) }, label = { Text(stringResource(R.string.task_title)) })

            Spacer(Modifier.size(40.dp))

            TextField(value = newTask.description, onValueChange = {
                    it -> newTask.title = it
            }, placeholder = { Text(stringResource(R.string.description)) }, label = { Text(stringResource(R.string.task_description)) })
        }
    }

}