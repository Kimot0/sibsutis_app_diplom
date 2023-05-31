package com.example.diplom.ui.schedule

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.databinding.ScheduleTabFragmentBinding
import com.example.diplom.domain.entity.Lesson
import com.example.diplom.utils.Status
import com.example.diplom.utils.collectOnStart
import com.example.diplom.utils.convertNumberToDayOfWeek
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleTabFragment : Fragment(R.layout.schedule_tab_fragment) {

    private lateinit var binding: ScheduleTabFragmentBinding
    private val viewModel: ScheduleViewModel by viewModel()
    private val adapter: ScheduleTabRecyclerAdapter = ScheduleTabRecyclerAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScheduleTabFragmentBinding.bind(view)
        arguments?.takeIf { it.containsKey("position") }?.apply {
            val position = getInt("position")
            position.convertNumberToDayOfWeek()
                ?.let { viewModel.getScheduleFromDatabase(InMemoryCache.group.groupID, it) }
        }
        bindUI()
        collectData()

    }

    fun bindUI() {
        with(binding) {
            rvScheduleTab.adapter = adapter
            rvScheduleTab.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun collectData() {
        collectOnStart(viewModel.dailyScheduleStateFlow) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data?.isEmpty() == true) {
                        binding.tvNoLesson.text = "No lessons"
                    } else {
                        it.data?.let { lessons -> adapter.setUpdatedData(lessons) }
                    }
                }
                Status.ERROR -> Unit
                Status.LOADING -> Unit
            }
        }
    }
}