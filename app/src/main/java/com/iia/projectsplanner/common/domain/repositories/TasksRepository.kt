package com.iia.projectsplanner.common.domain.repositories

import androidx.paging.PagingSource
import com.iia.projectsplanner.common.domain.model.Task

interface TasksRepository {
    fun getAllTasks(projectId: Int): PagingSource<Int, Task>
    suspend fun insert(task: Task)
}