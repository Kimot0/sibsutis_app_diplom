package com.example.diplom.ui.attendanceTeacher.detailedteacherattendance

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.databinding.DetailedTeacherAttendanceFragmentBinding
import com.example.diplom.domain.entity.HeadListForTeacher
import com.example.diplom.domain.entity.TeacherAttendance
import com.example.diplom.domain.entity.toTeacherAttendance
import com.example.diplom.utils.Status
import com.example.diplom.utils.collectOnStart
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailedTeacherAttendanceFragment : Fragment(R.layout.detailed_teacher_attendance_fragment) {

    companion object {
        const val DATE_FORMAT = "dd.MM.yyyy"
    }

    private val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)

    private lateinit var binding: DetailedTeacherAttendanceFragmentBinding
    private val model: DetailedTeacherAttendanceViewModel by viewModel()
    private val adapter: DetailedTeacherAttendanceRecyclerAdapter =
        DetailedTeacherAttendanceRecyclerAdapter()
//    private val tempLick: MutableList<TeacherAttendance> = mutableListOf(
//        TeacherAttendance(4, "ИП-915", "Саод", "Лекция", "Петя Остапенко", "11.01.2001"),
//        TeacherAttendance(5, "ИП-913", "ТРПО", "Практика", "Василий Латкин", "14.01.2001")
//    )

    private fun initAdapters() {
        binding.studentsRecyclerViewTeacher.adapter = adapter
        binding.studentsRecyclerViewTeacher.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailedTeacherAttendanceFragmentBinding.bind(view)
        initAdapters()
        val listIndex = arguments?.getInt("position") ?: 0
        collectData(listIndex)

    }

    private fun collectData(position: Int) {
        collectOnStart(model.listForDetailedTeacherStateFlow) {
            when (it.status) {
                Status.SUCCESS -> {
                    bindui(
                        it.data?.get(position) ?: HeadListForTeacher(
                            -1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            true
                        )
                    )
                }
                Status.ERROR -> Unit
                Status.LOADING -> Unit
            }
        }
    }

    private fun bindui(list: HeadListForTeacher) {
        with(binding) {
            with(list) {
                adapter.setUpdatedData(groupList)
                groupNameTeacher.text = group
                disciplineNameTeacher.text = discipline
                disciplineTypeTeacher.text = lessonType
                nameTeacher.text = teacherFIO
                lessonDateEditTextTeacher.setText(date)
            }
            backButtonDetailedTeacher.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonDoNotSendTeacher.setOnClickListener {
                findNavController().popBackStack()
            }

            buttonSendTeacher.setOnClickListener {
                model.sendGroupListByTeacher(
                    HeadListForTeacher(
                        id = 0,
                        group = groupNameTeacher.text.toString(),
                        discipline = disciplineNameTeacher.text.toString(),
                        lessonType = disciplineTypeTeacher.text.toString(),
                        teacherFIO = nameTeacher.text.toString(),
                        date = lessonDateEditTextTeacher.text.toString(),
                        confirm = true,
                        groupList = adapter.getresult()
                    )
                )
            }

            lessonDateEditTextTeacher.setOnClickListener {
                showDatePicker()
            }

            lessonDateTeacher.setOnClickListener {
                showDatePicker()
            }
        }
    }

    private fun showDatePicker() {
        val date = DateTime().withTimeAtStartOfDay().toCalendar(Locale.getDefault())

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.DialogTheme,
            { _, year, month, dayOfMonth ->
                date.set(Calendar.YEAR, year)
                date.set(Calendar.MONTH, month)
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                binding.lessonDateEditTextTeacher.setText(simpleDateFormat.format(Date(date.timeInMillis)))
            },
            date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = LocalDate.now().toDate().time
        datePickerDialog.show()
    }


}