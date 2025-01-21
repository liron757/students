package com.example.students

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class StudentDetails : AppCompatActivity() {
    private lateinit var tvStudentName: TextView
    private lateinit var tvStudentId: TextView
    private lateinit var cbStudentChecked: CheckBox
    private lateinit var tvStudentPhone: TextView
    private lateinit var tvStudentAddress: TextView
    private lateinit var btnEdit: Button
    private lateinit var editActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        tvStudentName = findViewById(R.id.tvStudentName)
        tvStudentId = findViewById(R.id.tvStudentId)
        cbStudentChecked = findViewById(R.id.cbStudentChecked)
        tvStudentPhone = findViewById(R.id.tvStudentPhone)
        tvStudentAddress = findViewById(R.id.tvStudentAddress)
        btnEdit = findViewById(R.id.btnEdit)

        val position = intent.getIntExtra("POSITION", -1)
        MainActivity.studentList[position].let {
            tvStudentName.text = it.name
            tvStudentId.text = it.id
            cbStudentChecked.isChecked = it.isChecked
            tvStudentPhone.text = it.phone
            tvStudentAddress.text = it.address
        }

        editActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val (updatedStudent, listPosition) = getUpdatedStudent(result)
                val resultIntent = Intent()
                resultIntent.putExtra("POSITION", listPosition)
                resultIntent.putExtra("UPDATED_STUDENT_ID", updatedStudent.id)
                resultIntent.putExtra("UPDATED_STUDENT_NAME", updatedStudent.name)
                resultIntent.putExtra("UPDATED_STUDENT_PHONE", updatedStudent.phone)
                resultIntent.putExtra("UPDATED_STUDENT_ADDRESS", updatedStudent.address)
                resultIntent.putExtra("UPDATED_STUDENT_CHECKED", updatedStudent.isChecked)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else if (result.resultCode == RESULT_CANCELED && result.data != null) {
                val (_, listPosition) = getUpdatedStudent(result)

                val resultIntent = Intent()
                resultIntent.putExtra("POSITION", listPosition)
                setResult(RESULT_CANCELED, resultIntent)
                finish()
            }
        }

        btnEdit.setOnClickListener {
            val intent = Intent(this, EditStudent::class.java)
            intent.putExtra("POSITION", position)

            editActivityResultLauncher.launch(intent)
        }
    }
}