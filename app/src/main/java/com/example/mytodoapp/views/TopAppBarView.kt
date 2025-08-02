package com.example.mytodoapp.views

import android.content.res.Resources.Theme
import android.graphics.drawable.Icon
import android.util.Log
import android.widget.Toolbar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytodoapp.R
import com.example.mytodoapp.ui.theme.MyToDoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TopAppBarView() {
    MyToDoAppTheme {
        CenterAlignedTopAppBar(
            title = { Text("Tasks") },
            navigationIcon = {
                Icon(
                    Icons.Default.Menu,
                    "Menu"
                )
            },
            actions = {
                Icon(
                    painter = painterResource(R.drawable.filter_list_icon),
                    contentDescription = "Sort"
                )

                IconButton(onClick = {}, content = {
                    Icon(
                        Icons.Default.MoreVert,
                        "More"
                    )
                })
            }


        )
    }
}