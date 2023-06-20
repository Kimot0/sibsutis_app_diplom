package com.example.diplom.ui.attendanceTeacher.detailedteacherattendance

import androidx.fragment.app.Fragment
import com.example.diplom.R
import com.example.diplom.databinding.ItemAttendanceApplicationBinding
import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.databinding.StudentAttendanceItemBinding
import com.example.diplom.domain.entity.Student
import com.example.diplom.domain.entity.StudentOfGroup

class DetailedTeacherAttendanceRecyclerAdapter : RecyclerView.Adapter<DetailedTeacherAttendanceRecyclerAdapter.ViewHolder>() {

    private val dataList: MutableList<StudentOfGroup> = mutableListOf()
    val templist: MutableMap<String,String> = mutableMapOf()

    private val name : MutableList<String> = mutableListOf()
    private val surname : MutableList<String> = mutableListOf()
    private val secondname : MutableList<String> = mutableListOf()
    private val attendance : MutableList<String> = mutableListOf()

    fun getresult():String = templist.toString()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(dataList: String) {
        val reslist: MutableList<StudentOfGroup> = mutableListOf()
        val tempdata = dataList.replace("\n"," ").split("\\s+".toRegex()).toMutableList()
        for(i in 0 until tempdata.size) {
            if (i % 4 == 0)
                surname.add(tempdata[i])
            if (i % 4 == 1)
                name.add(tempdata[i])
            if (i % 4 == 2)
                secondname.add(tempdata[i])
            if (i % 4 == 3)
                attendance.add(tempdata[i])
        }
        for (j in 0 until surname.size) {
            reslist.add(StudentOfGroup(j.toString(),(j+10).toString(),surname[j]+ " " + name[j] + " " + secondname[j]))
        }
        this.dataList.clear()
        this.dataList.addAll(reslist)
        var k = 0
        this.dataList.forEach {
            this.templist.put(it.fio, attendance[k])
            k++
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailedTeacherAttendanceRecyclerAdapter.ViewHolder {
        return ViewHolder(
            StudentAttendanceItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailedTeacherAttendanceRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    inner class ViewHolder(private val binding: StudentAttendanceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: StudentOfGroup) {
            binding.studentName.text = data.fio
            binding.attendanceButton.text = templist.getValue(data.fio)
            binding.attendanceButton.setOnClickListener {
                if (binding.attendanceButton.text == "Н") {
                    binding.attendanceButton.text = "П"
                    templist.put(data.fio,"П")
                } else {
                    binding.attendanceButton.text = "Н"
                    templist.put(data.fio,"Н")
                }
            }
        }
    }
}