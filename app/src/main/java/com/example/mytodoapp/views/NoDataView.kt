package com.example.mytodoapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mytodoapp.R


@Composable
fun NoDataView(innerPadding: PaddingValues) {

    Box(modifier = Modifier.padding(innerPadding).fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image( modifier = Modifier.size(80.dp), painter = painterResource(R.drawable.logo_no_fill), contentDescription = stringResource(R.string.no_task))
            Text(text = stringResource(R.string.you_have_no_tasks))
        }
        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp),
            onClick = {},
            containerColor = colorResource(R.color.colorAccent)
        ) {
            Icon(Icons.Filled.Add, "Add", tint = colorResource(R.color.white))
        }
    }

}
