package com.example.diplom.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.R
import com.example.diplom.databinding.ItemNewsBinding
import com.example.diplom.domain.entity.News

class NewsCardAdapter(
    private var dataList: MutableList<News>,
    private val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<NewsCardAdapter.NewsViewHolder>() {

    //private var dataList: MutableList<News> = mutableListOf()

    inner class NewsViewHolder(
        private val binding: ItemNewsBinding,
        private val onItemClick: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: News) {
            with(binding) {
                tvTitle.text = data.title
                tvContent.text = data.description
                tvAuthor.text = data.author
                tvDate.text = data.dateTime
                root.setOnClickListener {
                    onItemClick(adapterPosition)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }


    override fun getItemCount(): Int = dataList.size

}