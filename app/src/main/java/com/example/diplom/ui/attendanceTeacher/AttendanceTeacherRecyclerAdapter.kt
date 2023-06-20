package com.example.diplom.ui.attendanceTeacher

import android.annotation.SuppressLint
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.databinding.ItemAttendanceApplicationBinding
import com.example.diplom.domain.entity.TeacherAttendance

class AttendanceTeacherRecyclerAdapter(
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<AttendanceTeacherRecyclerAdapter.AttendanceTeacherViewHolder>() {

    private var dataList: MutableList<TeacherAttendance> = mutableListOf()


    inner class AttendanceTeacherViewHolder(
        private val binding: ItemAttendanceApplicationBinding,
        private val onItemClick: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TeacherAttendance) {
            with(binding) {
                groupTitleTextView.text = data.groupID
                disciplineTextView.text = data.discipline
                lessonTypeTextView.text = data.lessonType
                dataTextView.text = data.date
                root.setOnClickListener {
                    onItemClick(adapterPosition)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(dataList: List<TeacherAttendance>) {
        ///this.dataList = dataList as MutableList<TeacherAttendance>
        //this.dataList = dataList.toMutableList()
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AttendanceTeacherViewHolder, position: Int) {
        holder.bind(dataList[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceTeacherViewHolder {
        return AttendanceTeacherViewHolder(
            ItemAttendanceApplicationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }

    override fun getItemCount(): Int = dataList.size


}
