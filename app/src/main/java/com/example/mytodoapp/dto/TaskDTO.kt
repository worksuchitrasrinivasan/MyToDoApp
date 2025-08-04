package com.example.mytodoapp.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class TaskDTO(
        var id: Int = 0,
        var title: String = "",
        var description: String = "",
        var isDone: Boolean = false
    ): Parcelable
