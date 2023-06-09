package com.example.diplom.ui.schedule.scheduleSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.databinding.ItemScheduleSearchBinding
import com.example.diplom.domain.entity.ScheduleGroups

class ScheduleSearchRecyclerAdapter(private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<ScheduleSearchRecyclerAdapter.ScheduleSearchViewHolder>() {

    private var dataList: MutableList<ScheduleGroups> = mutableListOf()

    inner class ScheduleSearchViewHolder(
        private val binding: ItemScheduleSearchBinding,
        private val onItemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ScheduleGroups) {
            with(binding) {
                tvGroup.text = data.groupID
                root.setOnClickListener {
                    onItemClick(data.groupID)
                }
            }
        }
    }

    fun bindUpdatedData(groups: List<ScheduleGroups>?) {
        this.dataList.clear()
        if (groups != null) {
            this.dataList.addAll(groups)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ScheduleSearchRecyclerAdapter.ScheduleSearchViewHolder {
        return ScheduleSearchViewHolder(
            ItemScheduleSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onItemClick
        )
    }

    override fun onBindViewHolder(
        holder: ScheduleSearchRecyclerAdapter.ScheduleSearchViewHolder, position: Int
    ) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

}