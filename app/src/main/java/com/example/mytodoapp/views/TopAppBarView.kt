package com.example.mytodoapp.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.mytodoapp.R
import com.example.mytodoapp.ui.theme.MyToDoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(onClick: () -> Unit) {
    MyToDoAppTheme {
        CenterAlignedTopAppBar(
            title = { Text("Tasks") },
            navigationIcon = {
                IconButton(onClick = onClick) {
                    Icon(
                        Icons.Default.Menu,
                        "Menu"
                    )
                }
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