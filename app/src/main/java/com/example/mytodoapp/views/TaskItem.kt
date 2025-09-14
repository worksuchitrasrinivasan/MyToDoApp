package com.example.mytodoapp.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.dto.toTask
import com.example.mytodoapp.model.Task

@Composable
fun TaskItem(
    task: TaskDTO,
    edit: (task: TaskDTO) -> Unit,
    delete: (task: Task) -> Unit,
    update: (task: Task) -> Unit,
    showDeleteIcon: Boolean
) {

    Row(modifier = Modifier
        .clickable(onClick = { edit(task) }),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            task.isDone,
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary),
            onCheckedChange = {
                task.isDone = it
                update(task.toTask())
            }
        )

        Text(
            text = task.title,
            textDecoration = if(task.isDone) {
                TextDecoration.LineThrough
            } else {
                null
            },
            fontSize = 20.sp
        )

        Spacer(Modifier.weight(1f))

        if(showDeleteIcon) {
            IconButton(onClick = {
                delete(task.toTask())
            }, content = {
                Icon(
                    Icons.Default.Delete,
                    "Delete task",
                )
            })
        }

    }
}

@Composable
@Preview
fun TaskItemPreview() {
    val task = TaskDTO(0, "hello task", "", false)
    TaskItem(task, {}, {}, {}, true)
}