package com.example.mytodoapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mytodoapp.model.Task


@Composable
fun EditView(innerPadding: PaddingValues, task: Task?= null) {

    Column(modifier = Modifier.padding(innerPadding)) {
        TextField(task?.title ?: "", onValueChange = { it ->
            task?.title = it
        })

    }
}