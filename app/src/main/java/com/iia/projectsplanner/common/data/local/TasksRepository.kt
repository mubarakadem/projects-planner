package com.iia.projectsplanner.common.data.local

import androidx.paging.PagingSource
import com.iia.projectsplanner.common.data.model.Task

interface TasksRepository {
    fun getAllTasks(projectId: Int): PagingSource<Int, List<Task>>
    suspend fun insert(task: Task)
}