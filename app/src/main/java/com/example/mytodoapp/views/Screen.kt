package com.example.mytodoapp.views

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


sealed class Screen: NavKey {
    @Serializable
    data object DataScreen: Screen()
    @Serializable
    data object NoDataScreen: Screen()
    @Serializable
    data object EditScreen: Screen()
    @Serializable
    data object AddScreen: Screen()
}
