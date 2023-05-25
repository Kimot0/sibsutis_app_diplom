package com.example.diplom.ui.news.detailednews


import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.diplom.R
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.databinding.DetailedNewsFragmentBinding
import com.example.diplom.domain.entity.News
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedNewsFragment : Fragment(R.layout.detailed_news_fragment) {

    private lateinit var binding: DetailedNewsFragmentBinding
    private val model: DetailedNewsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailedNewsFragmentBinding.bind(view)
        val newsIndex = arguments?.getInt("position") ?: 0
        //bindui(InMemoryCache.news[newsIndex])
        viewLifecycleOwner.lifecycleScope.launch {
            model.deatailedNewsStateFlow.collect {
                bindui(it[newsIndex])
            }
        }

    }

    private fun bindui(news: News) {
        with(binding) {
            with(news) {
                tvItemTitle.text = title
                tvItemAuthor.text = author
                tvItemDate.text = dateTime
                tvItemDescription.text = content
            }
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}