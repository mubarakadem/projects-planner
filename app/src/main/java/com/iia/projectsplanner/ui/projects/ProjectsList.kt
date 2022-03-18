package com.iia.projectsplanner.ui.projects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iia.projectsplanner.util.PACKAGES_ROUTE
import com.ramcosta.composedestinations.annotation.Destination

@ExperimentalMaterial3Api
@Destination(route = PACKAGES_ROUTE, start = true)
@Composable
fun ProjectsList() {
    val projects = listOf(270f, 176f, 43f, 0f, 243f, 345f)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Projects",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (projects.isEmpty()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "No Projects",
                        fontSize = MaterialTheme.typography.displaySmall.fontSize
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                        Text(
                            text = "Click + to add project",
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }

            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(projects) {
                        ProjectItem(progress = it)
                    }
                }
            }
            FloatingActionButton(
                onClick = { },
                containerColor = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add new project button")
            }
        }
    }
}