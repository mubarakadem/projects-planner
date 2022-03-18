package com.iia.projectsplanner.common.data.local

import com.iia.projectsplanner.common.data.model.Project
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(private val projectsDao: Project.ProjectsDao): ProjectsRepository {
    override fun getAllProjects() = projectsDao.getAllProjects()

    override suspend fun insert(project: Project) {
        projectsDao.insert(project)
    }
}