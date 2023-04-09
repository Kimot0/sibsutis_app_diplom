package com.example.diplom.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.databinding.ScheduleTabFragmentBinding

class ScheduleTabFragment : Fragment(R.layout.schedule_tab_fragment) {

    private lateinit var binding :ScheduleTabFragmentBinding
    private val model : ScheduleViewModel = ScheduleViewModel()
    private val adapter:ScheduleTabRecyclerAdapter = ScheduleTabRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScheduleTabFragmentBinding.bind(view)
        with(binding){
            rvScheduleTab.adapter = adapter
            rvScheduleTab.layoutManager = LinearLayoutManager(context)
            arguments?.takeIf{ it.containsKey("position") }?.apply {
                val position = getInt("position")
                if(model.scheduleArray[position].isEmpty()) {
                    tvNoLesson.text = "Нет занятий"
                }
                adapter.setUpdatedData(model.scheduleArray[position])
            }
        }
    }
}