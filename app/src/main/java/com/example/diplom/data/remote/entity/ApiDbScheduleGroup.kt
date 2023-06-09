package com.example.diplom.data.remote.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diplom.domain.entity.ScheduleGroups

@Entity(
    tableName = "groupsSchedule",
)
data class ApiDbScheduleGroup(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "groupID")
    val groupID: String?,

)

fun ApiDbScheduleGroup.toScheduleGroup() = ScheduleGroups(
    groupID = groupID?:""
)

fun ApiDbScheduleGroup.toApiScheduleGroup() = ApiScheduleGroup(
    groupID = this.groupID?:"",
    id = this.id
)