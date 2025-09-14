package com.example.mytodoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.dto.TaskDTO
import com.example.mytodoapp.dto.toTaskDTO
import com.example.mytodoapp.model.Task
import com.example.mytodoapp.repository.TodoRepository
import com.example.mytodoapp.util.Async
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


data class TaskUiState(
    val tasks: List<TaskDTO> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val showDeleteIcon: Boolean = false
)

sealed class TaskUiEvent {
    data class MarkTaskDone(val task: Task): TaskUiEvent()
    data class DeleteTask(val task: Task): TaskUiEvent()
    data class AddTask(val task: Task): TaskUiEvent()
    data class UpdateTask(val task: Task): TaskUiEvent()
    data class ShowDeleteIcon(val showDeleteIcon: Boolean): TaskUiEvent()
}


@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository): ViewModel() {

    private val _taskUiState: MutableStateFlow<TaskUiState> = MutableStateFlow(TaskUiState(isLoading = true))
    val taskUiState: StateFlow<TaskUiState> = _taskUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskUiState(isLoading = true)
    )

    init {
        viewModelScope.launch {
            repository.getAllTasks().map { asyncTasks ->
                Timber.d( "getAllTasks")
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
            }.collect { value ->
                _taskUiState.update { it.copy(tasks = value.tasks) }
            }
        }
    }


    fun onEvent(event: TaskUiEvent) {
        when(event) {
            is TaskUiEvent.DeleteTask -> {
                Timber.d( "TaskUiEvent.DeleteTask")
                delete(event.task)
            }
            is TaskUiEvent.MarkTaskDone -> {
                Timber.d( "TaskUiEvent.MarkTaskDone")
                updateTask(event.task)
            }

            is TaskUiEvent.AddTask -> {
                Timber.d( "TaskUiEvent.AddTask")
                insertTask(event.task)
            }

            is TaskUiEvent.UpdateTask -> {
                Timber.d( "TaskUiEvent.UpdateTask")
                updateTask(event.task)
            }

            is TaskUiEvent.ShowDeleteIcon -> {
                Timber.d( "TaskUiEvent.ShowDeleteIcon")
                _taskUiState.update { it.copy(showDeleteIcon = event.showDeleteIcon) }
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
            Timber.d( "updateTask: $task")
            repository.updateTask(task)
        }
    }
    fun delete(task: Task) {
        viewModelScope.launch {
            Timber.d( "delete: $task")
            repository.deleteTask(task)
        }
    }

}