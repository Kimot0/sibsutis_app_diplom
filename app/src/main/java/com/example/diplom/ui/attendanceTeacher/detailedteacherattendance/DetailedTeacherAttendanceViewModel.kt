package com.example.diplom.ui.attendanceTeacher.detailedteacherattendance

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.data.remote.entity.ApiHeadSendList
import com.example.diplom.domain.entity.HeadListForTeacher
import com.example.diplom.domain.entity.TeacherGetGroupListRequest
import com.example.diplom.domain.entity.toHeadListForTeacher
import com.example.diplom.domain.repo.IGetHeadListRepo
import com.example.diplom.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class DetailedTeacherAttendanceViewModel (private val repo: IGetHeadListRepo): ViewModel() {

    private val _listForDetailedTeacherState = MutableStateFlow<Event<List<HeadListForTeacher>>?>(null)
    val listForDetailedTeacherStateFlow = _listForDetailedTeacherState.asStateFlow().filterNotNull()

    init {
        getHeadList(TeacherGetGroupListRequest("Дьячкова Ирина Сергеевна"))
    }
    fun getHeadList(teacherFIO: TeacherGetGroupListRequest) {
        viewModelScope.launch {
            _listForDetailedTeacherState.emit(Event.loading())
            val list = repo.getGetHeadList(teacherFIO)
            _listForDetailedTeacherState.emit(Event.success(list))
        }
    }

    fun sendGroupListByTeacher(grouplist: HeadListForTeacher) {
        viewModelScope.launch {
            val temp = repo.sendGroupListByTeacher(grouplist)
            Log.println(Log.WARN,"CUSTOM",temp)
        }
    }
}