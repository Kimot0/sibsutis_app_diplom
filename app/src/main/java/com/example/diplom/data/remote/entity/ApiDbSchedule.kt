package com.example.diplom.data.remote.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diplom.domain.entity.Lesson

@Entity(
    tableName = "schedule",
)
data class ApiDbSchedule(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo
    val lessonTime: String,
    @ColumnInfo
    val teacher: String,
    @ColumnInfo
    val classroom: String,
    @ColumnInfo
    val typeOfLesson: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val weekType: String,
    @ColumnInfo
    val groupID: String?,
    @ColumnInfo
    val weekDay: String,
)

fun ApiDbSchedule.toLesson() = Lesson(
    lessonTime = this.lessonTime,
    teacher = this.teacher,
    classroom = this.classroom,
    typeOfLesson = this.typeOfLesson,
    name = this.name,
    weekType = this.weekType,
    groupID = this.groupID ?: "",
    weekDay = this.weekDay,
)

fun ApiDbSchedule.toApiLesson() = ApiLesson(
    dayOfWeek = this.weekDay,
    groupID = this.groupID?:"",
    subjectName = this.name,
    subjectType = this.typeOfLesson,
    teacherName = this.teacher,
    startTime = this.lessonTime,
    studyRoom = this.classroom,
    weekType = this.weekType,
    id = this.id
)