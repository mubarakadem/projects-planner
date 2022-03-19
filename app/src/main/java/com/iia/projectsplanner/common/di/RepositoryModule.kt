package com.iia.projectsplanner.common.di

import com.iia.projectsplanner.common.data.local.ProjectsRepositoryImpl
import com.iia.projectsplanner.common.domain.repositories.ProjectsRepository
import com.iia.projectsplanner.common.domain.repositories.TasksRepository
import com.iia.projectsplanner.common.data.local.TasksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindProjectsRepository(projectsRepositoryImpl: ProjectsRepositoryImpl): ProjectsRepository

    @Binds
    @Singleton
    fun bindTasksRepository(tasksRepositoryImpl: TasksRepositoryImpl): TasksRepository
}