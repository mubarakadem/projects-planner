package com.iia.projectsplanner.common.data.local

import androidx.paging.PagingSource
import com.iia.projectsplanner.common.domain.model.Project
import com.iia.projectsplanner.common.domain.repositories.ProjectsRepository
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(private val projectsDao: Project.ProjectsDao):
    ProjectsRepository {
    override fun getAllProjects(): PagingSource<Int, Project> = projectsDao.getAllProjects()

    override suspend fun insert(project: Project) {
        projectsDao.insert(project)
    }
}