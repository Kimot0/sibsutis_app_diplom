package com.example.diplom.ui.schedule

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.databinding.ScheduleTabFragmentBinding
import com.example.diplom.ui.login.LoginFragment

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
                if(InMemoryCache.user.group == "IP-916"){
                    if(model.scheduleArray916[position].isEmpty()) {
                        tvNoLesson.text = "Нет занятий"
                    }
                    adapter.setUpdatedData(model.scheduleArray916[position])
                }else{
                    if(InMemoryCache.user.group == "IP-917"){
                        if(model.scheduleArray917[position].isEmpty()) {
                            tvNoLesson.text = "Нет занятий"
                        }
                        adapter.setUpdatedData(model.scheduleArray917[position])
                    }
                }
            }
        }
    }
}