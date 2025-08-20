package com.example.mytodoapp.views

import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import com.example.mytodoapp.R
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.dto.toTask
import com.example.mytodoapp.model.Task
import com.example.mytodoapp.ui.theme.MyToDoAppTheme
import com.example.mytodoapp.viewmodel.TodoViewModel


@Composable
fun EditView( backStack: NavBackStack, task: TaskDTO, edit: (task: Task) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarEditView(backStack) },
        floatingActionButton = { FloatingActionButton(Screen.AddScreen) {
            edit(task.toTask())
            backStack.removeLastOrNull()
        } },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                value = task.title,
                onValueChange = {  title -> task.title = title },
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
                value = task.description,
                onValueChange = { task.description = it },
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
    EditView(backStack = NavBackStack(), task = TaskDTO(1, "task", "Task Description", false), {})
}