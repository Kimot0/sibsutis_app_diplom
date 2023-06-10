package com.example.diplom.ui.news

import androidx.recyclerview.widget.DiffUtil
import com.example.diplom.domain.entity.News

class NewsDiffUtil(
    private val oldList:List<News>,
    private val newList:List<News>
):DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNews = oldList[oldItemPosition]
        val newNews = newList[newItemPosition]
        return oldNews.id == newNews.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}