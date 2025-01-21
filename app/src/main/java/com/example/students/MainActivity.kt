package com.example.students

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAdd: Button
    private lateinit var adapter: StudentAdapter

    companion object {
        val studentList = mutableListOf<Student>()
    }

    private lateinit var editActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvStudentsList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter()
        recyclerView.adapter = adapter

        btnAdd = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val intent = Intent(this, NewStudents::class.java)
            launchAddActivity(intent)
        }
        editActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val (updatedStudent, position) = getUpdatedStudent(result)

                if (position != -1) {
                    studentList[position] = updatedStudent
                    adapter.notifyItemChanged(position)
                }
            } else if (result.resultCode == RESULT_CANCELED && result.data != null) {
                val (_, position) = getUpdatedStudent(result)
                if (position != -1) {
                    studentList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }
            }
        }

        addActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val (updatedStudent) = getUpdatedStudent(result)
                studentList.add(updatedStudent)
                adapter.notifyItemInserted(studentList.size - 1)
            }
        }
    }

    fun launchAddActivity(intent: Intent) {
        addActivityResultLauncher.launch(intent)
    }

    fun launchEditActivity(intent: Intent) {
        editActivityResultLauncher.launch(intent)
    }
}