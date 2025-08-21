package com.example.mytodoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.dto.toTaskDTO
import com.example.mytodoapp.model.Task
import com.example.mytodoapp.repository.TodoRepository
import com.example.mytodoapp.util.Async
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


data class TaskUiState(
    val tasks: List<TaskDTO> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String = ""
)


@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository): ViewModel() {

    val taskUiState: StateFlow<TaskUiState> = repository.getAllTasks().map { asyncTasks ->
        when(asyncTasks) {
            Async.Loading -> TaskUiState(isLoading = true)

            is Async.Success -> {
                val taskDTOs = asyncTasks.data.map { it.toTaskDTO() }
                TaskUiState(tasks = taskDTOs)
            }

            is Async.Error -> {
                TaskUiState(errorMessage = asyncTasks.message)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskUiState(isLoading = true)
    )

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