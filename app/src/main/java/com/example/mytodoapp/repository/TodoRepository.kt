package com.example.mytodoapp.repository

import android.content.Context
import com.example.mytodoapp.database.TodoDatabase
import com.example.mytodoapp.model.Task
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TodoRepository @Inject constructor (@ApplicationContext context: Context) {
    val db = TodoDatabase.getDatabase(context)

    fun getAllTasks() = db.todoDAO().getAllTasks()

    suspend fun insertTask(task: Task) = db.todoDAO().insertTask(task)

    suspend fun updateTask(task: Task) = db.todoDAO().updateTask(task)

    suspend fun deleteTask(task: Task) = db.todoDAO().deleteTask(task)

}