package com.example.mytodoapp.repository

import com.example.mytodoapp.database.TodoDatabase
import com.example.mytodoapp.model.Task
import com.example.mytodoapp.util.Async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TodoRepository @Inject constructor () {

    @Inject
    lateinit var db: TodoDatabase

    fun getAllTasks(): Flow<Async<List<Task>>> = db.todoDAO().getAllTasks().map { tasks ->
        Async.Success(tasks)
    }

    suspend fun insertTask(task: Task) = db.todoDAO().insertTask(task)

    suspend fun updateTask(task: Task) = db.todoDAO().updateTask(task)

    suspend fun deleteTask(task: Task) = db.todoDAO().deleteTask(task)

}