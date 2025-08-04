package com.example.mytodoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.TodoApp.Companion.TAG
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.dto.toTaskDTO
import com.example.mytodoapp.model.Task
import com.example.mytodoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository): ViewModel() {

    private val _tasksFlow = MutableStateFlow<List<TaskDTO>>(emptyList())
    val tasksFlow: StateFlow<List<TaskDTO>> = _tasksFlow

    fun getAllTasks() {
        viewModelScope.launch {
            Log.d(TAG, "getAllTasks()")
            repository.getAllTasks().catch {
                Log.d(TAG, "some error occurred while getting all tasks $it")
            }.collect {
                Log.d(TAG, "newTaskList: $it")
                _tasksFlow.value = it.map { task ->
                    task.toTaskDTO()
                }
            }
        }
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            Log.d(TAG, "insertTask: $task")
            repository.insertTask(task)
            getAllTasks()
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
            getAllTasks()
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            getAllTasks()
        }
    }

}