package com.example.mytodoapp.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose.MyToDoAppTheme
import com.example.mytodoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(onClick: () -> Unit) {
    MyToDoAppTheme {
        CenterAlignedTopAppBar(
            modifier = Modifier.shadow(elevation = 10.dp),
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
            },

            expandedHeight = TopAppBarDefaults.MediumAppBarCollapsedHeight,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),

            )
    }
}