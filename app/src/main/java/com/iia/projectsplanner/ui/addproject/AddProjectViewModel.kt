package com.iia.projectsplanner.ui.addproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iia.projectsplanner.common.domain.model.Project
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProjectViewModel @Inject constructor(
    private val projectsDao: Project.ProjectsDao
) : ViewModel() {
    fun insert(project: Project) {
        viewModelScope.launch {
            projectsDao.insert(project)
        }
    }
}