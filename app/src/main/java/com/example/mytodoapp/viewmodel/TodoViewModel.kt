package com.example.mytodoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.dto.toTaskDTO
import com.example.mytodoapp.model.Task
import com.example.mytodoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository): ViewModel() {

    private val _tasksFlow = MutableStateFlow<List<TaskDTO>>(emptyList())
    val tasksFlow: StateFlow<List<TaskDTO>> = _tasksFlow.asStateFlow()

    fun getAllTasks() {
        viewModelScope.launch {
            Timber.d("getAllTasks()")
            repository.getAllTasks().catch {
                Timber.d( "some error occurred while getting all tasks $it")
            }.collect {
                Timber.d( "newTaskList: $it")
                _tasksFlow.value = it.map { task ->
                    task.toTaskDTO()
                }
            }
        }
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            Timber.d( "insertTask: $task")
            repository.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

}