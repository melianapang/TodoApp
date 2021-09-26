package com.dicoding.todoapp.ui.add

import androidx.lifecycle.*
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.data.TaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel (private val taskRepository: TaskRepository): ViewModel() {
    private val _task = MutableLiveData<Task>()

    fun setTask(taskValue: Task?) {
        if (taskValue == _task.value) {
            return
        }
        _task.value = taskValue
    }

    fun insertTask() {
        viewModelScope.launch {
            _task.value?.let { taskRepository.insertTask(it) }
        }
    }
}