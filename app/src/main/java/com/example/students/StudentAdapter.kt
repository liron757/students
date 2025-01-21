package com.example.students

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.students.MainActivity.Companion.studentList
import com.example.students.databinding.StudentItemBinding

class StudentAdapter() :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    class StudentViewHolder(val binding: StudentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.tvStudentName.text = student.name
            binding.cbStudentChecked.isChecked = student.isChecked
            binding.ivStudentProfile.setImageResource(R.drawable.male_avatar)
            binding.tvStudentId.text = student.id

            binding.cbStudentChecked.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                val updatedStudent = student.copy(isChecked = isChecked)
                if (position != RecyclerView.NO_POSITION) {
                    studentList[position] = updatedStudent
                }
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, StudentDetails::class.java)
                intent.putExtra("POSITION", adapterPosition)
                (itemView.context as MainActivity).launchEditActivity(intent)
            }
        }
    }
}