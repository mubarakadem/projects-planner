package com.iia.projectsplanner.common.domain.repositories

import androidx.paging.PagingSource
import com.iia.projectsplanner.common.domain.model.Project

interface ProjectsRepository {
    fun getAllProjects(): PagingSource<Int, Project>
    suspend fun insert(project: Project)
}