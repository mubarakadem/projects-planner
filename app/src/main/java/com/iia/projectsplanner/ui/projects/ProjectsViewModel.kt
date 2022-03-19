package com.iia.projectsplanner.ui.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.iia.projectsplanner.common.domain.repositories.ProjectsRepository
import com.iia.projectsplanner.common.domain.model.Project
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(private val projectsRepository: ProjectsRepository): ViewModel(){
    val projects = Pager(config = PagingConfig(pageSize = 10)){
        projectsRepository.getAllProjects()
    }.flow.cachedIn(viewModelScope)

    fun insert(project: Project){
        viewModelScope.launch {
            projectsRepository.insert(project)
        }
    }
}