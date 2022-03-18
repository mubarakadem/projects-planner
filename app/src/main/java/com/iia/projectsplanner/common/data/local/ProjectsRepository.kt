package com.iia.projectsplanner.common.data.local

import androidx.paging.PagingSource
import com.iia.projectsplanner.common.data.model.Project

interface ProjectsRepository {
    fun getAllProjects(): PagingSource<Int, List<Project>>
    suspend fun insert(project: Project)
}