package com.example.mytodoapp.dto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class TaskDTO(
    val id: Int = 0,
    title: String = "",
    description: String = "",
    isDone: Boolean = false
) {

    var title by mutableStateOf(title)
    var description by mutableStateOf(description)
    var isDone by mutableStateOf(isDone)

}
