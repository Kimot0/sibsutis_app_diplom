package com.example.diplom.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.R
import com.example.diplom.databinding.NewsFragmentBinding
import com.example.diplom.domain.entity.News

class NewsFragment : Fragment(R.layout.news_fragment) {

    private lateinit var binding: NewsFragmentBinding
    private var dataList: MutableList<News> = mutableListOf(
        News("Hi", "I'm egor", "07.04.2023", "Egor"),
        News("Hi", "I'm nick", "03.04.2023", "Nick"),
        News("Hi", "I'm artem", "12.04.2023", "Artem"),
        News("Hi", "I'm sasha", "01.04.2023", "Sasha")
    )
    private val adapterNews: NewsCardAdapter = NewsCardAdapter(dataList,this::click)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NewsFragmentBinding.bind(view)
        bindui()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun bindui() {
        with(binding) {
            with(newsRecycler) {
                adapter = adapterNews
                layoutManager = LinearLayoutManager(requireContext())
            }
            with(swipeNews){
                setOnRefreshListener {

                    isRefreshing=false
                }
            }
        }
    }
    private fun click(position:Int){
        val bundle = Bundle()
        bundle.putInt("position", position)
        Toast.makeText(context, "U clicked on news", Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            R.id.action_navigation_news_to_detailed_news,
            bundle
        )
    }


}