package com.iia.projectsplanner.ui.projects

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iia.projectsplanner.R

@ExperimentalMaterial3Api
@Composable
fun ProjectItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            ProjectAvatar()
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "Call Blocker",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                    Text(
                        text = "Call blocker allows you to block contacts logs spam and numbers from calling you",
                        fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun ProjectAvatar(imageUrl: String? = null, name: String? = null) {
    val density = LocalDensity.current
    val primary = MaterialTheme.colorScheme.primary
    val outline = MaterialTheme.colorScheme.outline.copy(.10f)
    val arcBorderSize = with(density) { 4.dp.toPx() }

    Box(
        modifier = Modifier
            .size(56.dp)
            .padding(2.dp)
            .drawBehind {
                drawArc(
                    color = primary,
                    useCenter = false,
                    startAngle = 270f,
                    sweepAngle = 270f,
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
        Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center) {
            if (!imageUrl.isNullOrEmpty()) {
                Image(
                    painter = painterResource(id = R.mipmap.nejat),
                    contentDescription = "Project Icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            } else {
                if (!name.isNullOrEmpty()) {
                    TextDrawable(name = name, modifier = Modifier.size(40.dp))
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
    ProjectItem()
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