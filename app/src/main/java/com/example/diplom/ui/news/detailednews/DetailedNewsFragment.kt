package com.example.diplom.ui.news.detailednews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.diplom.R
import com.example.diplom.databinding.DetailedNewsFragmentBinding
import com.example.diplom.domain.entity.News
import com.example.diplom.utils.Status
import com.example.diplom.utils.collectOnStart
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedNewsFragment : Fragment(R.layout.detailed_news_fragment) {

    private lateinit var binding: DetailedNewsFragmentBinding
    private val viewModel: DetailedNewsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailedNewsFragmentBinding.bind(view)
        val newsIndex = arguments?.getInt("position") ?: 0
        collectData(newsIndex)
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
    private fun collectData(newsIndex:Int) {
        collectOnStart(viewModel.deatailedNewsStateFlow) {
            when (it.status) {
                Status.SUCCESS -> {
                    bindui(it.data!![newsIndex])
                }
                Status.ERROR -> Unit
                Status.LOADING -> Unit
            }
        }
    }
}