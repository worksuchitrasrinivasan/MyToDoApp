package com.example.mytodoapp.dto

data class TaskDTO(
        var id: Int = 0,
        var title: String = "",
        var description: String = "",
        var isDone: Boolean = false
    )
