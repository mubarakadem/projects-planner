package com.iia.projectsplanner.ui.projects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.iia.projectsplanner.common.ui.TopBarTitle
import com.iia.projectsplanner.common.util.PROJECTS_ROUTE
import com.iia.projectsplanner.ui.destinations.AddProjectDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Destination(route = PROJECTS_ROUTE, start = true)
@Composable
fun ProjectsList(
    projectsViewModel: ProjectsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val projects = projectsViewModel.projects.collectAsLazyPagingItems()
    val uiState = rememberProjectState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TopBarTitle("Projects")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (projects.itemCount <= 0) {
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
                        if (it != null) {
                            ProjectItem(it, uiState)
                        }
                    }
                }
            }
            FloatingActionButton(
                onClick = {
                    navigator.navigate(AddProjectDestination)
                },
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
