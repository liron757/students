package com.example.students

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudent : AppCompatActivity() {

    private lateinit var etStudentName: EditText
    private lateinit var etStudentId: EditText
    private lateinit var cbStudentChecked: CheckBox
    private lateinit var etStudentPhone: EditText
    private lateinit var etStudentAddress: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        etStudentName = findViewById(R.id.etStudentName)
        etStudentId = findViewById(R.id.etStudentId)
        cbStudentChecked = findViewById(R.id.cbStudentChecked)
        etStudentPhone = findViewById(R.id.etStudentPhone)
        etStudentAddress = findViewById(R.id.etStudentAddress)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
        btnDelete = findViewById(R.id.btnDelete)

        val position = intent.getIntExtra("POSITION", -1)
        MainActivity.studentList[position].let {
            etStudentName.setText(it.name)
            etStudentId.setText(it.id)
            cbStudentChecked.isChecked = it.isChecked
            etStudentPhone.setText(it.phone)
            etStudentAddress.setText(it.address)
        }

        btnSave.setOnClickListener {
            val resultIntent = Intent()

            resultIntent.putExtra("POSITION", position)
            resultIntent.putExtra("UPDATED_STUDENT_ID", etStudentId.text.toString())
            resultIntent.putExtra("UPDATED_STUDENT_NAME", etStudentName.text.toString())
            resultIntent.putExtra("UPDATED_STUDENT_PHONE", etStudentPhone.text.toString())
            resultIntent.putExtra("UPDATED_STUDENT_ADDRESS", etStudentAddress.text.toString())
            resultIntent.putExtra("UPDATED_STUDENT_CHECKED", cbStudentChecked.isChecked)

            setResult(RESULT_OK, resultIntent)

            finish()
        }
        btnCancel.setOnClickListener { finish() }

        btnDelete.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("POSITION", position)
            setResult(RESULT_CANCELED, resultIntent)
            finish()
        }
    }
}