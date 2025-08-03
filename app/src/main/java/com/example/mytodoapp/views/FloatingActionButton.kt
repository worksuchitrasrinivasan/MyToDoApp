package com.example.mytodoapp.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.mytodoapp.R

@Composable
fun FloatingActionButton() {
    FloatingActionButton(
        onClick = {},
        containerColor = colorResource(R.color.colorAccent)
    ) {
        Icon(Icons.Filled.Add, "Add", tint = colorResource(R.color.white))
    }
}