package com.example.mytodoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytodoapp.dao.TodoDAO
import com.example.mytodoapp.model.Task


@Database(version = 1 ,entities = [Task::class])
abstract class TodoDatabase(): RoomDatabase() {
    abstract fun todoDAO(): TodoDAO

}