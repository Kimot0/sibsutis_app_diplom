package com.example.diplom.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.diplom.R
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.databinding.ScheduleFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : Fragment(R.layout.schedule_fragment) {

    private lateinit var binding: ScheduleFragmentBinding
    private val viewModel: ScheduleViewModel by viewModel()
    private lateinit var adapterSch: ScheduleViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScheduleFragmentBinding.bind(view)
        adapterSch = ScheduleViewPagerAdapter(this)
        viewModel.getShedule(InMemoryCache.group)
        bindui()
    }

    private fun bindui() {
        with(binding) {
            scheduleViewPager.adapter = adapterSch
            TabLayoutMediator(scheduleTabLayout, scheduleViewPager) { tab, position ->
                tab.text = viewModel.tabTitles[position]
            }.attach()
            groupTextViewShowing.text = InMemoryCache.group.groupID
            scheduleSearchButton.setOnClickListener {
                findNavController().navigate(R.id.action_schedule_to_search_schedule)
            }
        }
    }
}