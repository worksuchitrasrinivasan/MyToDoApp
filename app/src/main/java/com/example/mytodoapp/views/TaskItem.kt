package com.example.mytodoapp.views

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.mytodoapp.R
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.dto.toTask
import com.example.mytodoapp.model.Task

@Composable
fun TaskItem(task: TaskDTO, edit: (task: TaskDTO) -> Unit, delete: () -> Unit, update: (task: Task) -> Unit) {

    Row(modifier = Modifier
        .combinedClickable(
            onClick = { edit(task) },
            onLongClick = { delete() }
            ),
        verticalAlignment = Alignment.CenterVertically) {

        Checkbox(
            task.isDone,
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary),
            onCheckedChange = {
                task.isDone = it
                update(task.toTask())
            }
        )

        Text(
            modifier = Modifier.fillMaxSize(),
            text = task.title,
            textDecoration = if(task.isDone) {
                TextDecoration.LineThrough
            } else {
                null
            },
            fontSize = 20.sp
        )

    }
}