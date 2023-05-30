package com.example.diplom.ui.schedule.scheduleSearch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.diplom.R
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.databinding.ScheduleSearchFragmentBinding
import com.example.diplom.domain.entity.ScheduleRequest
import kotlinx.coroutines.launch

class ScheduleSearchFragment : Fragment(R.layout.schedule_search_fragment) {

    private lateinit var binding: ScheduleSearchFragmentBinding
    private lateinit var request:ScheduleRequest

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScheduleSearchFragmentBinding.bind(view)
        bindUi()
    }
    private fun bindUi(){
        with(binding){
            searchButton.setOnClickListener{
                lifecycleScope.launch {
                    request = ScheduleRequest(searchEditText.text.toString())
                    if(searchEditText.text.toString().isNotEmpty()){
                        InMemoryCache.group = request
                        findNavController().navigate(R.id.action_navigation_schedule_search_to_schedule)
                    }else{
                        findNavController().navigate(R.id.action_navigation_schedule_search_to_schedule)
                    }
                }
            }
        }
    }
}