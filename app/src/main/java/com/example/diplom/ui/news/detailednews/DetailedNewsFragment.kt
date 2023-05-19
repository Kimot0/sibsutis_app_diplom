package com.example.diplom.ui.news.detailednews


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.diplom.R
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.databinding.DetailedNewsFragmentBinding
import com.example.diplom.domain.entity.News
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedNewsFragment : Fragment(R.layout.detailed_news_fragment) {

    private lateinit var binding: DetailedNewsFragmentBinding
    private val model: DetailedNewsViewModel by viewModel()
    /*private var dataList: MutableList<News> = mutableListOf(
        News("Hi", "I'm egor", "07.04.2023", "Egor"),
        News("Hi", "I'm nick", "03.04.2023", "Nick"),
        News("Hi", "I'm artem", "12.04.2023", "Artem"),
        News("Hi", "I'm sasha", "01.04.2023", "Sasha")
    )*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailedNewsFragmentBinding.bind(view)
        val newsIndex = arguments?.getInt("position") ?: 0
        bindui(InMemoryCache.news[newsIndex])
    }

    private fun bindui(news: News) {
        with(binding) {
            with(news) {
                tvItemTitle.text = title
                tvItemAuthor.text = author
                tvItemDate.text = dateTime
                tvItemDescription.text = content
            }
        }
    }
}