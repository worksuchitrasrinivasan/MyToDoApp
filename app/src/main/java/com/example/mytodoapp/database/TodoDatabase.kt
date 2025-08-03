package com.example.mytodoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytodoapp.dao.TodoDAO
import com.example.mytodoapp.model.Task


@Database(version = 1 ,entities = [Task::class])
abstract class TodoDatabase(): RoomDatabase() {

    abstract fun todoDAO(): TodoDAO

    companion object {
        @Volatile
        private var INSTANCE:TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, TodoDatabase::class.java, "todo_database")
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}