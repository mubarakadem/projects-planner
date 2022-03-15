package com.iia.projectsplanner.ui.projects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
    val projects = listOf<String>()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        if (projects.isEmpty()){
            item {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Text(text = "No Projects", fontSize = MaterialTheme.typography.bodyMedium.fontSize)
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                            Text(text = "Click + to add project", fontSize = MaterialTheme.typography.labelMedium.fontSize)
                        }
                    }
                }
            }
        }
        else{
            items(projects){
                ProjectItem()
            }
        }
    }
}