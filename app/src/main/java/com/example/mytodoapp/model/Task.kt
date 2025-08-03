package com.example.mytodoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    var isDone: Boolean = false
)