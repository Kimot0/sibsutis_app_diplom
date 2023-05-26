package com.example.diplom.ui.attendance

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.databinding.AttendanceFragmentBinding
import org.joda.time.DateTime
import org.joda.time.LocalDate
import java.text.SimpleDateFormat
import java.util.*

class AttendanceFragment : Fragment(R.layout.attendance_fragment) {

    companion object {
        const val DATE_FORMAT = "dd.MM.yyyy"
    }

    private val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)

    private var disciplines: MutableList<String> = mutableListOf(
        "Технологии разработки программного обеспечения",
        "СиАОД",
        "Представление графической информации",
    )

    private var disciplineTypes: MutableList<String> = mutableListOf(
        "Лекция",
        "Практическое занятие",
        "Лабораторное занятие"
    )

    private var students: MutableList<String> = mutableListOf(
        "Александрова Ангелина",
        "Бауэр Егор",
        "Бондаренко Антон",
        "Давыдков Никита",
        "Зырянов Константин",
        "Иванов Артём",
        "Исмаилов Рухид",
        "Истомина Анна",
        "Косарева Екатерина",
        "Костин Кирилл",
        "Кульбезекова Кристина",
        "Меньщиков Данил",
        "Мониев Никита",
        "Подковыров Артем",
        "Сотников Владимир"
    )

    private lateinit var binding: AttendanceFragmentBinding
    private lateinit var disciplineNameAdapter: ArrayAdapter<String>
    private lateinit var disciplineTypeAdapter: ArrayAdapter<String>

    private val adapter: AttendanceRecyclerAdapter = AttendanceRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AttendanceFragmentBinding.bind(view)
        initAdapters()
        initClickListeners()
    }

    private fun initAdapters() {
        disciplineTypeAdapter = ArrayAdapter(requireContext(), R.layout.common_item, disciplineTypes)
        disciplineNameAdapter = ArrayAdapter(requireContext(), R.layout.common_item, disciplines)

        binding.disciplineNameSpinner.adapter = disciplineNameAdapter
        binding.disciplineTypeSpinner.adapter = disciplineTypeAdapter

        binding.studentsRecyclerView.adapter = adapter
        binding.studentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter.setUpdatedData(students)
    }

    private fun initClickListeners() {
        with(binding) {
            lessonDateEditText.setOnClickListener {
                showDatePicker()
            }
            lessonDateField.setOnClickListener {
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

                binding.lessonDateEditText.setText(simpleDateFormat.format(Date(date.timeInMillis)))
            },
            date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = LocalDate.now().toDate().time
        datePickerDialog.show()
    }
}