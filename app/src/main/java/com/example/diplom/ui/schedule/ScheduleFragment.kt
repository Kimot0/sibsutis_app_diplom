package com.example.diplom.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diplom.R
import com.example.diplom.databinding.ScheduleFragmentBinding
import com.example.diplom.domain.entity.Lesson
import com.google.android.material.tabs.TabLayoutMediator

class ScheduleFragment : Fragment(R.layout.schedule_fragment) {

    private lateinit var binding: ScheduleFragmentBinding
    private val model : ScheduleViewModel = ScheduleViewModel()
    private lateinit var adapterSch: ScheduleViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScheduleFragmentBinding.bind(view)
        adapterSch = ScheduleViewPagerAdapter(this)
        bindui()
    }

    fun bindui() {
        with(binding) {
            scheduleViewPager.adapter = adapterSch
            TabLayoutMediator(scheduleTabLayout, scheduleViewPager) { tab, position ->
                tab.text =model.tabTitles[position]
            }.attach()
        }
    }
}