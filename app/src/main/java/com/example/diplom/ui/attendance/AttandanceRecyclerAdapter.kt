package com.example.diplom.ui.attendance

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.data.remote.entity.ApiStudentOfGroup
import com.example.diplom.databinding.StudentAttendanceItemBinding
import com.example.diplom.domain.entity.StudentOfGroup

class AttendanceRecyclerAdapter : RecyclerView.Adapter<AttendanceRecyclerAdapter.ViewHolder>() {

    private val dataList: MutableList<StudentOfGroup> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(dataList: List<StudentOfGroup>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttendanceRecyclerAdapter.ViewHolder {
        return ViewHolder(
            StudentAttendanceItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AttendanceRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    inner class ViewHolder(private val binding: StudentAttendanceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: StudentOfGroup) {
            binding.studentName.text = data.fio
            binding.attendanceButton.setOnClickListener {
                if (binding.attendanceButton.text == "Н") {
                    binding.attendanceButton.text = ""
                } else {
                    binding.attendanceButton.text = "Н"
                }
            }
        }
    }
}