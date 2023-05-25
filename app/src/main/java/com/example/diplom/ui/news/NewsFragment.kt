package com.example.diplom.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.databinding.NewsFragmentBinding
import com.example.diplom.domain.entity.News
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.news_fragment) {

    private lateinit var binding: NewsFragmentBinding
    private val adapterNews: NewsCardAdapter = NewsCardAdapter(this::onItemClick)
    private val model: NewsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NewsFragmentBinding.bind(view)
        viewLifecycleOwner.lifecycleScope.launch {
            model.newsStateFlow.collect {
                adapterNews.setUpdatedData(it)
            }
        }
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
                    model.getNews()
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


}