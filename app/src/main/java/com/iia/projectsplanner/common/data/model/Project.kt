package com.iia.projectsplanner.common.data.model

import androidx.paging.PagingSource
import androidx.room.*

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val progress: Float = 0f,
    val icon: String? = null,
    val version: String? = null,
    val description: String? = null,
){
    @Dao
    interface ProjectDao{
        @Query("SELECT * FROM projects")
        fun getAllProjects(): PagingSource<Int, List<Project>>

        @Insert
        suspend fun insert(project: Project)
    }
}
