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
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleTabFragment : Fragment(R.layout.schedule_tab_fragment) {

    private lateinit var binding: ScheduleTabFragmentBinding
    private val model: ScheduleViewModel by viewModel()
    private val adapter: ScheduleTabRecyclerAdapter = ScheduleTabRecyclerAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScheduleTabFragmentBinding.bind(view)
        bindUI()

    }

     fun bindUI() {
        with(binding) {
            rvScheduleTab.adapter = adapter
            rvScheduleTab.layoutManager = LinearLayoutManager(context)
            arguments?.takeIf { it.containsKey("position") }?.apply {
                val position = getInt("position")
                Log.println(Log.WARN, "Custom", position.toString())
                Log.println(Log.WARN, "Custom", "capacity = "+InMemoryCache.groupSchedule.size.toString())
                InMemoryCache.groupSchedule.forEach { it ->
                    it.forEach { 
                        Log.println(Log.WARN,"Custom",it.toString())
                    }
                }
                if (InMemoryCache.groupSchedule[position].isEmpty()) {
                    tvNoLesson.text = "Нет занятий"
                }
                adapter.setUpdatedData(InMemoryCache.groupSchedule[position])
            }
        }
    }
}