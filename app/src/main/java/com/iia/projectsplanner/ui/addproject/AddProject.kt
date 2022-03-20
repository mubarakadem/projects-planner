package com.iia.projectsplanner.ui.addproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iia.projectsplanner.R
import com.iia.projectsplanner.common.domain.model.Project
import com.iia.projectsplanner.common.ui.TopBarTitle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Destination
@Composable
fun AddProject(
    addProjectViewModel: AddProjectViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState: AddProjectState = rememberAddProjectState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TopBarTitle(title = "Add Project") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Projects List",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        addProjectViewModel.insert(Project(name = uiState.name))
                        navigator.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Add Project",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ) {
        val visualTransformation = VisualTransformation.None

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(76.dp)
                        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .graphicsLayer(shadowElevation = 4f, clip = false, shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gallery_picker_selected),
                        contentDescription = "Pick Image from gallery",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FormField(
                        value = uiState.name,
                        label = "Name",
                        visualTransformation = visualTransformation,
                        enabled = true,
                        isError = uiState.isValid(),
                        onValueChange = {
                            uiState.name = it
                        }
                    )
                    FormField(
                        value = uiState.version,
                        label = "Version",
                        visualTransformation = visualTransformation,
                        enabled = true,
                        isError = uiState.isValid(),
                        onValueChange = {
                            uiState.version = it
                        },
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            FormField(
                value = uiState.description,
                label = "Description",
                visualTransformation = visualTransformation,
                enabled = true,
                isError = uiState.isValid(),
                onValueChange = {
                    uiState.description = it
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun FormField(
    value: String,
    visualTransformation: VisualTransformation,
    enabled: Boolean,
    isError: Boolean,
    modifier: Modifier = Modifier,
    label: String? = null,
    onValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        decorationBox = {
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                innerTextField = it,
                placeholder = {
                    label?.let { placeholder ->
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                            androidx.compose.material.Text(text = placeholder)
                        }
                    }
                },
                enabled = enabled,
                isError = isError,
                singleLine = true,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                    start = 0.dp,
                    bottom = 0.dp
                )
            )
        },
        modifier = modifier
            .indicatorLine(
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(0.5f),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary
                )
            )
            .fillMaxWidth()
    )
}
