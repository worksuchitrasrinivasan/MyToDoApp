package com.example.mytodoapp.views

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.example.mytodoapp.R
import com.example.mytodoapp.dto.TaskDTO

@Composable
fun TaskItem(task: TaskDTO) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            task.isDone,
            colors = CheckboxDefaults.colors(checkedColor = colorResource(R.color.colorAccent)),
            onCheckedChange = {
                task.isDone = it
            },
        )

        if(task.isDone) {
            Text(text = task.title, style = TextStyle(textDecoration = TextDecoration.LineThrough))
        } else {
            Text(text = task.title)
        }
    }
}