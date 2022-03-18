package com.iia.projectsplanner.common.data.local

import com.iia.projectsplanner.common.data.model.Task
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(private val tasksDao: Task.TasksDao) :
    TasksRepository {
    override fun getAllTasks(projectId: Int) = tasksDao.getAllTasks(projectId)

    override suspend fun insert(task: Task) {
        tasksDao.insert(task)
    }

}