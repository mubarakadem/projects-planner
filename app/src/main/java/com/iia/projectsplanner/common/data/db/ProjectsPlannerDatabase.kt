package com.iia.projectsplanner.common.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iia.projectsplanner.common.domain.model.Project
import com.iia.projectsplanner.common.domain.model.Task

@Database(entities = [Project::class, Task::class], version = 1)
abstract class ProjectsPlannerDatabase: RoomDatabase() {
    abstract fun projectsDao(): Project.ProjectsDao
    abstract fun tasksDao(): Task.TasksDao
}