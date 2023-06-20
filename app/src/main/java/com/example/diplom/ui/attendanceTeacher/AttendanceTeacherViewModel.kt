package com.example.diplom.ui.attendanceTeacher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.data.remote.network.NetworkErrors
import com.example.diplom.domain.entity.HeadListForTeacher
import com.example.diplom.domain.entity.TeacherGetGroupListRequest
import com.example.diplom.domain.entity.toHeadListForTeacher
import com.example.diplom.domain.repo.IGetHeadListRepo
import com.example.diplom.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class AttendanceTeacherViewModel (private val repo: IGetHeadListRepo): ViewModel() {

    private val _listForTeacherState = MutableStateFlow<Event<List<HeadListForTeacher>>?>(null)
    val listForTeacherStateFlow = _listForTeacherState.asStateFlow().filterNotNull()

    init {
        getHeadList(TeacherGetGroupListRequest("Дьячкова Ирина Сергеевна"))
    }
    fun getHeadList(teacherFIO: TeacherGetGroupListRequest) {
        viewModelScope.launch {
            _listForTeacherState.emit(Event.loading())
            val list = repo.getGetHeadList(teacherFIO)
            _listForTeacherState.emit(Event.success(list))
        }
    }
}