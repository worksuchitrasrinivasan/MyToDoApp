package com.example.mytodoapp.dto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable


@Serializable
class TaskDTO(
    val id: Int = 0,
    var mTitle: String = "",
    var mDescription: String = "",
    var mDate: String = "",
    var mIsDone: Boolean = false,
) {

    var title by mutableStateOf(mTitle)
    var description by mutableStateOf(mDescription)
    var isDone by mutableStateOf(mIsDone)

}
