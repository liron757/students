package com.example.students

import androidx.activity.result.ActivityResult

data class Student(
    val id: String,
    val name: String,
    val phone: String,
    val address: String,
    val isChecked: Boolean = false
)

fun getUpdatedStudent(result: ActivityResult): Pair<Student, Int> {
    val data = result.data

    val position = data?.getIntExtra("POSITION", -1) ?: -1
    val updatedStudentId = data?.getStringExtra("UPDATED_STUDENT_ID") ?: ""
    val updatedStudentName = data?.getStringExtra("UPDATED_STUDENT_NAME") ?: ""
    val updatedStudentPhone = data?.getStringExtra("UPDATED_STUDENT_PHONE") ?: ""
    val updatedStudentAddress = data?.getStringExtra("UPDATED_STUDENT_ADDRESS") ?: ""
    val updatedStudentChecked =
        data?.getBooleanExtra("UPDATED_STUDENT_CHECKED", false) ?: false

    return Pair(
        Student(
            updatedStudentId,
            updatedStudentName,
            updatedStudentPhone,
            updatedStudentAddress,
            updatedStudentChecked
        ),
        position
    )
}