package com.iia.projectsplanner.common.data.model

import androidx.paging.PagingSource
import androidx.room.*
import com.iia.projectsplanner.util.TABLE_TASKS

@Entity(tableName = TABLE_TASKS)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "project_id")
    val projectId: Int,
    val title: String = "My Task",
    val finished: Boolean = false,
    @ColumnInfo(name = "start_date")
    val startDate: String? = null,
    @ColumnInfo(name = "end_date")
    val endDate: String? = null,
    val priority: String? = null,
    val notes: String? = null,
){
    @Dao
    interface TasksDao{

        @Query("SELECT * FROM tasks WHERE project_id = :projectId")
        fun getAllTasks(projectId: Int): PagingSource<Int, List<Task>>

        @Insert
        suspend fun insert(task: Task)
    }
}