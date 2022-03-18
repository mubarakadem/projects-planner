package com.iia.projectsplanner.common.di

import com.iia.projectsplanner.common.data.local.ProjectRepositoryImpl
import com.iia.projectsplanner.common.data.local.ProjectsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindProjectsRepository(projectRepositoryImpl: ProjectRepositoryImpl): ProjectsRepository
}