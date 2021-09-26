package com.dicoding.todoapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.ui.list.TaskActivity
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var detViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val factory = ViewModelFactory.getInstance(this)
        detViewModel = ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)
        //private val taskViewModel by viewModels<DetailTaskViewModel>()

        val edTitle = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val edDesc = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val edDueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        val btnDelete = findViewById<Button>(R.id.btn_delete_task)

        val bundle = intent.extras
        if(bundle != null){
            val taskId = bundle.getInt(TASK_ID)
            detViewModel.setTaskId(taskId)
            detViewModel.task.observe(this) {
                if(it != null) {
                    edTitle.setText(it.title, TextView.BufferType.EDITABLE)
                    edDesc.setText(it.description, TextView.BufferType.EDITABLE)
                    edDueDate.setText(DateConverter.convertMillisToString(it.dueDateMillis), TextView.BufferType.EDITABLE)
                }
            }
        }

        btnDelete.setOnClickListener {
            detViewModel.deleteTask()
            startActivity(Intent(this, TaskActivity::class.java))
        }
    }
}