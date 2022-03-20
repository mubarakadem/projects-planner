package com.iia.projectsplanner.common.ui

import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TopBarTitle(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = MaterialTheme.typography.headlineSmall.fontSize
    )
}