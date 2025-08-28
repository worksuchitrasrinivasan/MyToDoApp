package com.example.mytodoapp.views

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.mytodoapp.R


@Composable
fun FloatingActionButton(route: Screen, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier.imePadding(),
        onClick = onClick,
        containerColor = colorResource(R.color.colorAccent),
        shape = CircleShape
    ) {
        if (route == Screen.EditScreen || route == Screen.AddScreen) {
            Icon(Icons.Filled.Done, "Done", tint = colorResource(R.color.white))
        } else {
            Icon(Icons.Filled.Add, "Add", tint = colorResource(R.color.white))
        }
    }
}
