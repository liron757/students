package com.example.students

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewStudents : AppCompatActivity() {
    private lateinit var etStudentName: EditText
    private lateinit var etStudentId: EditText
    private lateinit var etStudentPhone: EditText
    private lateinit var etStudentAddress: EditText
    private lateinit var cbStudentChecked: CheckBox
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_students)

        etStudentName = findViewById(R.id.etStudentName)
        etStudentId = findViewById(R.id.etStudentId)
        etStudentPhone = findViewById(R.id.etStudentPhone)
        etStudentAddress = findViewById(R.id.etStudentAddress)
        cbStudentChecked = findViewById(R.id.cbStudentChecked)

        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)

        btnSave.setOnClickListener {
            val studentName = etStudentName.text.toString()
            val studentId = etStudentId.text.toString()
            val studentPhone = etStudentPhone.text.toString()
            val studentAddress = etStudentAddress.text.toString()
            val studentChecked = cbStudentChecked.isChecked

            val intent = intent

            intent.putExtra("UPDATED_STUDENT_ID", studentId)
            intent.putExtra("UPDATED_STUDENT_NAME", studentName)
            intent.putExtra("UPDATED_STUDENT_PHONE", studentPhone)
            intent.putExtra("UPDATED_STUDENT_ADDRESS", studentAddress)
            intent.putExtra("UPDATED_STUDENT_CHECKED", studentChecked)

            setResult(RESULT_OK, intent)
            finish()
        }

        btnCancel.setOnClickListener {
            finish()
        }

    }
}