package com.example.mytodoapp.views

import androidx.navigation3.runtime.NavKey
import com.example.mytodoapp.dto.TaskDTO
import kotlinx.serialization.Serializable


sealed class Screen: NavKey {
    @Serializable
    data object DataScreen: Screen()
    @Serializable
    class EditScreen(val task: TaskDTO): Screen()
    @Serializable
    object DeleteScreen: Screen()
    @Serializable
    data object AddScreen: Screen()
}
