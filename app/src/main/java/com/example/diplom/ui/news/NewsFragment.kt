package com.example.diplom.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.databinding.NewsFragmentBinding
import com.example.diplom.utils.Status
import com.example.diplom.utils.collectOnStart
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.news_fragment) {

    private lateinit var binding: NewsFragmentBinding
    private val adapterNews: NewsCardAdapter = NewsCardAdapter(this::onItemClick)
    private val viewModel: NewsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NewsFragmentBinding.bind(view)
        collectData()
        bindui()
    }
    private fun bindui() {
        with(binding) {
            with(newsRecycler) {
                adapter = adapterNews
                layoutManager = LinearLayoutManager(requireContext())
            }
            with(swipeNews) {
                setOnRefreshListener {
                    viewModel.getNews()
                    isRefreshing = false
                }
            }
        }
    }

    private fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        findNavController().navigate(
            R.id.action_navigation_news_to_detailed_news,
            bundle
        )
    }

    private fun collectData() {
        collectOnStart(viewModel.newsStateFlow) {
            when (it.status) {
                Status.SUCCESS -> {
                    adapterNews.setUpdatedData(it.data)
                }
                Status.ERROR -> Unit
                Status.LOADING -> Unit
            }
        }
    }
}