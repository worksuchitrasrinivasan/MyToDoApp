package com.example.mytodoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import com.example.mytodoapp.R
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.dto.toTask
import com.example.mytodoapp.model.Task
import com.example.mytodoapp.ui.theme.MyToDoAppTheme
import kotlinx.coroutines.launch


@Composable
fun EditView( backStack: NavBackStack, taskDTO: TaskDTO?, edit: (task: Task) -> Unit) {
    var title by rememberSaveable { mutableStateOf(taskDTO?.mTitle ?: "") }
    var description by rememberSaveable { mutableStateOf(taskDTO?.mDescription ?: "") }
    val task = taskDTO?.toTask() ?: Task(0, title, description, false)
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarEditView(backStack) },
        floatingActionButton = { FloatingActionButton(Screen.AddScreen) {
            scope.launch {
                if(task.title.isNotBlank()) {
                    edit(task)
                } else {
                    snackBarHostState.showSnackbar("Can not add empty task")
                }
                backStack.removeLastOrNull()
            }
        } },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                value = title,
                onValueChange = { task.title = it
                    title = it },
                placeholder = { Text(stringResource(R.string.title)) },
                label = { Text(stringResource(R.string.task_title)) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    disabledContainerColor = Color.White
                )

            )

            Spacer(Modifier.size(40.dp))

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp).padding(bottom = 100.dp)
                .fillMaxHeight(),
                value = description,
                onValueChange = { task.description = it
                                  description = it },
                placeholder = { Text(stringResource(R.string.description)) },
                label = { Text(stringResource(R.string.task_description)) },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledContainerColor = Color.White
                ))

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarEditView(backStack: NavBackStack){
    MyToDoAppTheme {
        TopAppBar(
            title = { Text("Edit Task") },
            navigationIcon = {
                IconButton(onClick = {backStack.removeLastOrNull()}) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        "Back"
                    )
                }
            }
        )
    }
}

@Composable
@Preview
fun PreviewEditView(){
    EditView(backStack = NavBackStack(),
        taskDTO = TaskDTO(1, "task", "Task Description", false),
        edit = {})
}