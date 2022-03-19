package com.iia.projectsplanner.common.domain.model

import androidx.paging.PagingSource
import androidx.room.*
import com.iia.projectsplanner.common.util.TABLE_PROJECTS

@Entity(tableName = TABLE_PROJECTS)
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
    interface ProjectsDao{
        @Query("SELECT * FROM projects")
        fun getAllProjects(): PagingSource<Int, Project>

        @Insert
        suspend fun insert(project: Project)
    }
}
