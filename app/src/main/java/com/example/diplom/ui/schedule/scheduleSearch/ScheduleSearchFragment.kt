package com.example.diplom.ui.schedule.scheduleSearch

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.databinding.ScheduleSearchFragmentBinding
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.utils.Status
import com.example.diplom.utils.collectOnStart
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleSearchFragment : Fragment(R.layout.schedule_search_fragment) {

    private lateinit var binding: ScheduleSearchFragmentBinding
    private val viewModel: ScheduleSearchViewModel by viewModel()
    private lateinit var request: ScheduleRequest
    private val adapterSearch: ScheduleSearchRecyclerAdapter = ScheduleSearchRecyclerAdapter(this::onItemClick)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScheduleSearchFragmentBinding.bind(view)
        viewModel.getGroups()
        collectData()
        bindUi()
    }

    //TODO: TEXTWATCHER

    private fun bindUi() {
        with(binding) {
            with(recyclerViewGroups){
                adapter = adapterSearch
                layoutManager = LinearLayoutManager(requireContext())
            }
            searchButton.setOnClickListener {
                lifecycleScope.launch {
                    request = ScheduleRequest(searchEditText.text.toString())
                    if (searchEditText.text.toString().isNotEmpty()) {
                        InMemoryCache.group = request
                        findNavController().navigate(R.id.action_navigation_schedule_search_to_schedule)
                    } else {
                        findNavController().navigate(R.id.action_navigation_schedule_search_to_schedule)
                    }
                }
            }
            searchBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun onItemClick(idGroup: String) {
        binding.searchEditText.setText(idGroup)
    }

    private fun collectData() {
        collectOnStart(viewModel.scheduleSearchStateFlow) {
            when (it.status) {
                Status.SUCCESS -> {
                    adapterSearch.bindUpdatedData(it.data)
                }
                Status.ERROR -> {
                    Log.println(Log.WARN,"Custom","bad data")
                }
                Status.LOADING -> Unit
            }
        }
    }
}