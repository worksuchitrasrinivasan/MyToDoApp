package com.example.mytodoapp.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import com.example.mytodoapp.R
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.dto.toTask
import com.example.mytodoapp.model.Task
import com.example.mytodoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


@Composable
fun DeleteView(backStack: NavBackStack, viewModel: TodoViewModel = hiltViewModel()) {

    val tasks by viewModel.tasksFlow.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarView() },
        floatingActionButton = { FloatingActionButton(Screen.AddScreen) {
            backStack.removeLastOrNull()
        } },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Text(text = stringResource(R.string.all_tasks))
            Spacer(Modifier.size(30.dp))
            LazyColumn {
                items(items=tasks, key = { it.id }) { task ->
                    DeleteTaskItem(task) { deleteTask ->
                        scope.launch {
                            viewModel.delete(deleteTask)
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun DeleteTaskItem(task: TaskDTO, deleteTask: (task: Task) -> Unit) {

    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {

        Checkbox(
            task.isDone,
            colors = CheckboxDefaults.colors(checkedColor = colorResource(R.color.colorAccent)),
            onCheckedChange = {
                Timber.d("On delete screen check can not be changed")
            },
            modifier = Modifier.clickable(
                enabled = false,
                onClick = {},
            )
        )

        Text(
            text = task.title, textDecoration = if (task.isDone) {
                TextDecoration.LineThrough
            } else {
                null
            }
        )

        Spacer(
            Modifier.weight(1f)
        )

        Icon(
            Icons.Filled.Close,
            "delete",
            Modifier
                .padding(20.dp)
                .clickable {
                deleteTask(task.toTask()
                )
            },
            tint = colorResource(R.color.red)
        )

    }
}