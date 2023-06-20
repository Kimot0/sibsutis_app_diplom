package com.example.diplom.data.remote.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diplom.domain.entity.HeadListForTeacher
import com.example.diplom.domain.entity.TeacherAttendance

@Entity(
    tableName = "listforteacher"
)
data class ApiDbListForTeacher(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "group")
    val group: String?,
    @ColumnInfo(name = "discipline")
    val discipline: String?,
    @ColumnInfo(name = "lessonType")
    val lessonType: String?,
    @ColumnInfo(name = "teacherFIO")
    val teacherFIO: String?,
    @ColumnInfo(name = "date")
    val date: String?,
    @ColumnInfo(name = "groupList")
    val groupList: String?,
)

fun ApiDbListForTeacher.toGetHeadList() = TeacherAttendance(
    id = this.id,
    groupID = this.group?:"",
    discipline = this.discipline?:"",
    lessonType = this.lessonType?:"",
    teacherFIO = this.teacherFIO?:"",
    date = this.date?:""
)

fun ApiDbListForTeacher.toGetHeadListForTeacher() = HeadListForTeacher(
    id = this.id,
    group = this.group?:"",
    discipline = this.discipline?:"",
    lessonType = this.lessonType?:"",
    teacherFIO = this.teacherFIO?:"",
    date = this.date?:"",
    groupList = this.groupList?:"",
    confirm = true
)