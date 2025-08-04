package com.example.mytodoapp.dto

import com.example.mytodoapp.model.Task

fun Task.toTaskDTO(): TaskDTO {
    return TaskDTO(id, title, description, isDone)
}

fun TaskDTO.toTask(): Task {
    return Task(id, title, description, isDone)
}