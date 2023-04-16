package com.example.diplom.ui.attendance

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.diplom.R
import com.example.diplom.databinding.AttendanceFragmentBinding
import com.example.diplom.utils.DisciplineType

class AttendanceFragment : Fragment(R.layout.attendance_fragment) {

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
        "Бауэр Егор",
        "Бондаренко Антон",
        "Мониев Никита",
        "Иванов Артем",
        "Макс Ярыгин",
        "Володимир Сотников",
        "Fucking slaves",
        "Boss of the gym",
        "Boy next door"
    )

    private lateinit var binding: AttendanceFragmentBinding
    private lateinit var disciplineNameAdapter: ArrayAdapter<String>
    private lateinit var disciplineTypeAdapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AttendanceFragmentBinding.bind(view)
        initAdapters()
    }

    private fun initAdapters() {
        disciplineTypeAdapter = ArrayAdapter(requireContext(), R.layout.common_item, disciplineTypes)
        disciplineNameAdapter = ArrayAdapter(requireContext(), R.layout.common_item, disciplines)

        binding.disciplineNameSpinner.adapter = disciplineNameAdapter
        binding.disciplineTypeSpinner.adapter = disciplineTypeAdapter
    }
}