package com.iia.projectsplanner.ui.projects

import android.os.Environment
import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iia.projectsplanner.common.domain.model.Project

@ExperimentalMaterial3Api
@Composable
fun ProjectItem(project: Project, uiState: ProjectsState, modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { }
    ) {
        ProjectAvatar(uiState = uiState, icon = project.icon, progress = project.progress)
        Text(
            text = project.name,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize
        )
    }
}

@Composable
fun ProjectAvatar(
    uiState: ProjectsState,
    name: String = "",
    icon: String? = null,
    @FloatRange(from = 0.0, to = 360.0) progress: Float
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val primary = MaterialTheme.colorScheme.primary
    val outline = MaterialTheme.colorScheme.outline.copy(.10f)
    val arcBorderSize = with(density) { 6.dp.toPx() }
    val angle = derivedStateOf { progress / 100 * 360 }

    Box(
        modifier = Modifier
            .size(72.dp)
            .padding(3.dp)
            .drawBehind {
                drawArc(
                    color = primary,
                    useCenter = false,
                    startAngle = 270f,
                    sweepAngle = angle.value,
                    style = Stroke(width = arcBorderSize)
                )
                drawArc(
                    color = outline,
                    useCenter = false,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    style = Stroke(width = arcBorderSize)
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.size(56.dp), contentAlignment = Alignment.Center) {
            if (!icon.isNullOrEmpty()) {
                Image(
                    bitmap = uiState.getBitmap(
                        parent = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath,
                        child = icon
                    ),
                    contentDescription = "Project Icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                )
            } else {
                if (name.isNotEmpty()) {
                    TextDrawable(name = name, modifier = Modifier.size(56.dp))
                }
            }
        }
    }
}

@Composable
fun TextDrawable(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary.copy(.5f),
                shape = CircleShape
            )
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun ProjectItemPreview() {
    ProjectItem(
        project = Project(name = "Call Blocker", progress = 125f),
        uiState = rememberProjectState()
    )
}

@Preview
@Composable
fun ProjectAvatarPreview() {
    ProjectAvatar(uiState = rememberProjectState(), icon = "vf", progress = 270f)
}

@Preview
@Composable
fun TextDrawablePreview() {
    TextDrawable("AB")
}