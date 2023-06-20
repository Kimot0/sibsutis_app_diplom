package com.example.diplom.ui.attendanceTeacher

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.databinding.TeacherAttendanceFragmentBinding
import com.example.diplom.domain.entity.TeacherAttendance
import com.example.diplom.domain.entity.TeacherGetGroupListRequest
import com.example.diplom.domain.entity.toTeacherAttendance
import com.example.diplom.utils.Status
import com.example.diplom.utils.collectOnStart
import org.koin.androidx.viewmodel.ext.android.viewModel

class AttendanceTeacherFragment : Fragment(R.layout.teacher_attendance_fragment) {
    private lateinit var binding: TeacherAttendanceFragmentBinding
    private val adapterAttendanceTeacher = AttendanceTeacherRecyclerAdapter(this::onItemClick)
    private val model: AttendanceTeacherViewModel by viewModel ()
    //    private val model:
    private val tempLick: MutableList<TeacherAttendance> = mutableListOf(
        TeacherAttendance(4, "ИП-915", "Саод", "Лекция", "Петя Остапенко", "11.01.2001"),
        TeacherAttendance(5, "ИП-913", "ТРПО", "Практика", "Василий Латкин", "14.01.2001")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TeacherAttendanceFragmentBinding.bind(view)
        collectData()
        bindui()
        ///adapterAttendanceTeacher.setUpdatedData(tempLick)

    }

    private fun collectData() {
        collectOnStart(model.listForTeacherStateFlow) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 -> adapterAttendanceTeacher.setUpdatedData(it1.map { it2 -> it2.toTeacherAttendance() }) }
                }
                Status.ERROR -> Unit
                Status.LOADING -> Unit
            }
        }
    }

    private fun bindui() {
        with(binding) {
            with(teacherRecycler) {
                adapter = adapterAttendanceTeacher
                layoutManager = LinearLayoutManager(requireContext())
            }
            with(swipeGroupForTeacher) {
                setOnRefreshListener {
                    model.getHeadList(TeacherGetGroupListRequest("Дьячкова Ирина Сергеевна"))
                    collectData()
                    isRefreshing = false
                }
            }
        }
    }

    private fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        findNavController().navigate(
            R.id.action_navigation_attendance_to_detailed_attendance,
            bundle
        )
    }
}
