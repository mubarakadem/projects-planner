package com.iia.projectsplanner.ui.projects

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iia.projectsplanner.R
import com.iia.projectsplanner.common.domain.model.Project

@ExperimentalMaterial3Api
@Composable
fun ProjectItem(project: Project, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable {  }
    ) {
        ProjectAvatar(imageUrl = project.icon, progress = project.progress)
        Text(
            text = project.name,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
    }
}

@Composable
fun ProjectAvatar(
    imageUrl: String? = null,
    name: String? = null,
    @FloatRange(from = 0.0, to = 360.0) progress: Float = 270f
) {
    val density = LocalDensity.current
    val primary = MaterialTheme.colorScheme.primary
    val outline = MaterialTheme.colorScheme.outline.copy(.10f)
    val arcBorderSize = with(density) { 6.dp.toPx() }

    Box(
        modifier = Modifier
            .size(72.dp)
            .padding(3.dp)
            .drawBehind {
                drawArc(
                    color = primary,
                    useCenter = false,
                    startAngle = 270f,
                    sweepAngle = progress,
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
            if (!imageUrl.isNullOrEmpty()) {
                Image(
                    painter = painterResource(id = R.mipmap.nejat),
                    contentDescription = "Project Icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                )
            } else {
                if (!name.isNullOrEmpty()) {
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
    ProjectItem(project = Project(name = "Call Blocker", progress = 125f))
}

@Preview
@Composable
fun ProjectAvatarPreview() {
    ProjectAvatar(imageUrl = "vf")
}

@Preview
@Composable
fun TextDrawablePreview() {
    TextDrawable("AB")
}