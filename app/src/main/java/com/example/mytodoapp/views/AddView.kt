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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mytodoapp.R
import com.example.mytodoapp.model.Task


@Composable
fun AddView( navController: NavHostController, save: (task: Task) -> Unit) {

    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarView() },
        floatingActionButton = { FloatingActionButton(ADD_VIEW) {
            if(title.isNotEmpty()) {
                save(Task(0, title, description, false))
            }
            navController.popBackStack()
        } },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            TextField(title, onValueChange = { it ->
                title = it
            }, placeholder = { Text(stringResource(R.string.title)) }, label = { Text(stringResource(R.string.task_title)) })

            Spacer(Modifier.size(40.dp))

            TextField(value = description, onValueChange = {
                    it -> description = it
            }, placeholder = { Text(stringResource(R.string.description)) }, label = { Text(stringResource(R.string.task_description)) })
        }
    }

}