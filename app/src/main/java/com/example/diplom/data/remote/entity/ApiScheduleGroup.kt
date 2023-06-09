package com.example.diplom.data.remote.entity

import com.example.diplom.domain.entity.ScheduleGroups
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiScheduleGroup(
    @Json(name = "id")
    val id:Int,
    @Json(name = "groupID")
    val groupID:String
)

fun ApiScheduleGroup.toApiDbScheduleGroup() = ApiDbScheduleGroup(
    id = id,
    groupID = groupID
)

fun ApiScheduleGroup.toScheduleGroups() = ScheduleGroups(
    groupID = groupID
)