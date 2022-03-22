package com.iia.projectsplanner.ui.projects

import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.iia.projectsplanner.common.domain.repositories.ProjectsRepository
import com.iia.projectsplanner.common.domain.model.Project
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(private val projectsRepository: ProjectsRepository): ViewModel(){
    val projects = Pager(config = PagingConfig(pageSize = 10)){
        projectsRepository.getAllProjects()
    }.flow.cachedIn(viewModelScope)

    init {
        collectIcons()
    }



    private fun collectIcons() {
        viewModelScope.launch {
            projects.collect { value ->

            }
        }
    }
}