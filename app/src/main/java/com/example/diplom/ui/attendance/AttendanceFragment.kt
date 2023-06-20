package com.example.diplom.ui.attendance

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.data.remote.entity.ApiHeadSendList
import com.example.diplom.databinding.AttendanceFragmentBinding
import com.example.diplom.domain.entity.DisciplineDbEntity
import com.example.diplom.ui.login.LoginViewModel
import com.example.diplom.utils.Status
import com.example.diplom.utils.collectOnStart
import com.example.diplom.utils.showLongToast
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.Map

class AttendanceFragment : Fragment(R.layout.attendance_fragment) {

    companion object {
        const val DATE_FORMAT = "dd.MM.yyyy"
    }

    private val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)

    private val viewModel: AttendanceViewModel by viewModel()

    private lateinit var binding: AttendanceFragmentBinding
    private lateinit var disciplineNameAdapter: ArrayAdapter<String>
    private lateinit var disciplineTypeAdapter: ArrayAdapter<String>
    private var tempdiscipline: String = ""
    private var templessontype: String = ""
    private val tempgrouplist: MutableList<String> = mutableListOf()

    private val adapter: AttendanceRecyclerAdapter = AttendanceRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AttendanceFragmentBinding.bind(view)

        viewModel.getDisciplines()
        viewModel.getGroup()

        initAdapters()
        initClickListeners()
        collectGroupState()
        collectDisciplineState()
        collectDisciplineTypeState()
        collectTeacherFioState()
    }

    private fun collectGroupState() {
        collectOnStart(viewModel.groupState) { event ->
            when (event.status) {

                Status.LOADING -> {
                    showLoading()
                }

                Status.SUCCESS -> {
                    showContent()
                    event.data?.let { adapter.setUpdatedData(it) }
                    event.data?.forEach {
                        tempgrouplist.add(it.fio)
                    }
                    binding.groupName.text = event.data?.first()?.studentGroup
                }

                Status.ERROR -> {
                    showContent()
                    requireContext().showLongToast("Ошибка получения списка группы")
                }
            }
        }
    }

    private fun collectDisciplineState() {
        collectOnStart(viewModel.disciplinesState) { event ->
            when (event.status) {

                Status.LOADING -> {
                    showLoading()
                }

                Status.SUCCESS -> {
                    val disciplines = event.data?.map {
                        it.discipline
                    }?.toSet()
                    disciplineNameAdapter = ArrayAdapter(
                        requireContext(),
                        R.layout.common_item,
                        disciplines?.toList() ?: emptyList()
                    )
                    binding.disciplineNameSpinner.adapter = disciplineNameAdapter
                }

                Status.ERROR -> {
                    showContent()
                    requireContext().showLongToast("Ошибка получения списка группы")
                }
            }
        }
    }

    private fun collectDisciplineTypeState() {
        collectOnStart(viewModel.disciplineTypesState) { event ->
            when (event.status) {
                Status.LOADING -> {
                }

                Status.SUCCESS -> {
                    disciplineTypeAdapter =
                        ArrayAdapter(
                            requireContext(),
                            R.layout.common_item,
                            event.data ?: emptyList()
                        )

                    binding.disciplineTypeSpinner.adapter = disciplineTypeAdapter
                }

                Status.ERROR -> {
                    showContent()
                    requireContext().showLongToast("Ошибка получения списка группы")
                }
            }
        }
    }

    private fun collectTeacherFioState() {
        collectOnStart(viewModel.teacherFioState) { event ->
            when (event.status) {
                Status.LOADING -> {
                }

                Status.SUCCESS -> {
                    binding.teacherNameEditText.setText(event.data.toString())
                }

                Status.ERROR -> {
                    showContent()
                    requireContext().showLongToast("Ошибка получения списка группы")
                }
            }
        }
    }

    private fun initAdapters() {
        binding.studentsRecyclerView.adapter = adapter
        binding.studentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initClickListeners() {
        with(binding) {
            lessonDateEditText.setOnClickListener {
                showDatePicker()
            }

            lessonDateField.setOnClickListener {
                showDatePicker()
            }

            disciplineNameSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val discipline = disciplineNameSpinner.adapter.getItem(position) as String
                    viewModel.getDisciplineTypes(discipline)
                    tempdiscipline = discipline
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

            disciplineTypeSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parrent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val lessonType = disciplineTypeSpinner.adapter.getItem(position) as String
                    viewModel.getTecherFIO(tempdiscipline, lessonType)
                    templessontype = lessonType
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }

            buttonSend.setOnClickListener {
                val sendlist = ApiHeadSendList(
                    id = 3,
                    group = groupName.text.toString(),
                    discipline = tempdiscipline,
                    lessonType = templessontype,
                    teacherFIO = teacherNameEditText.text.toString(),
                    date = lessonDateEditText.text.toString(),
                    groupList = adapter.templist,
                    confirm = false
                )

                viewModel.sendGroupList(sendlist)
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

    private fun showLoading() {
        binding.loader.visibility = View.VISIBLE
    }

    private fun showContent() {
        binding.loader.visibility = View.GONE
    }
}