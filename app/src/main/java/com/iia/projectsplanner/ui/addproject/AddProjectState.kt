package com.iia.projectsplanner.ui.addproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class AddProjectState {
    var name: String = ""
    var version: String = ""
    var description: String = ""

    fun isValid() = name.isNotEmpty()
}

@Composable
fun rememberAddProjectState() = remember { AddProjectState() }