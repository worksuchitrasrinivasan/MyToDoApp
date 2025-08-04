package com.example.mytodoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var isDone: Boolean = false
) {

}