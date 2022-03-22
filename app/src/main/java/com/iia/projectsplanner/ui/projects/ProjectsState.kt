package com.iia.projectsplanner.ui.projects

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File

class ProjectsState {
    fun getBitmap(parent: String, child: String): ImageBitmap{
        return BitmapFactory.decodeFile(File(parent, child).absolutePath).asImageBitmap()
    }
}

@Composable
fun rememberProjectState() = remember { ProjectsState() }