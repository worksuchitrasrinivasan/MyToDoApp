package com.example.mytodoapp.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.example.mytodoapp.dto.TaskDTO
import com.google.gson.Gson

const val DATA_VIEW = "data_view"
const val NO_DATA_VIEW = "no_data_view"
const val EDIT_VIEW = "edit_view"

const val ADD_VIEW = "add_view"



object TaskNavType: NavType<TaskDTO>(isNullableAllowed = false) {

    override fun put( bundle: SavedState, key: String, value: TaskDTO) {
        return bundle.putParcelable(key, value)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun get(bundle: SavedState, key: String): TaskDTO? {
        return bundle.getParcelable(key, TaskDTO::class.java)
    }

    override fun parseValue(value: String): TaskDTO {
        return Gson().fromJson(value, TaskDTO::class.java)
    }

}