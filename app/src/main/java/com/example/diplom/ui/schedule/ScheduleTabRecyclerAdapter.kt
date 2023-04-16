package com.example.diplom.ui.schedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.databinding.ItemScheduleListBinding
import com.example.diplom.domain.entity.Lesson

class ScheduleTabRecyclerAdapter: RecyclerView.Adapter<ScheduleTabRecyclerAdapter.ViewHolder>() {

    private val dataList: MutableList<Lesson> = mutableListOf()

    fun setUpdatedData(dataList: List<Lesson>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemScheduleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Lesson) {
            with(binding) {
                with(data) {
                    tvLessonClassroom.text = classroom
                    tvLessonDate.text = lessonTime
                    tvLessonName.text = name
                    tvLessonType.text = typeOfLesson
                    tvLessonTeacher.text = teacher
                    tvLessonWeek.text = weeekType
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemScheduleListBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}