package com.iia.projectsplanner.common.di

import android.content.Context
import androidx.room.Room
import com.iia.projectsplanner.common.data.db.ProjectsPlannerDatabase
import com.iia.projectsplanner.util.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): ProjectsPlannerDatabase {
        return Room.databaseBuilder(context, ProjectsPlannerDatabase::class.java, DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideProjectsDao(projectsPlannerDatabase: ProjectsPlannerDatabase) = projectsPlannerDatabase.projectsDao()

    @Provides
    @Singleton
    fun provideTasksDao(projectsPlannerDatabase: ProjectsPlannerDatabase) = projectsPlannerDatabase.tasksDao()
}