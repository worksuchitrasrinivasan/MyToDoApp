package com.example.mytodoapp.hiltmodules.data

import android.content.Context
import androidx.room.Room
import com.example.mytodoapp.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModules {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room
                .databaseBuilder(context, TodoDatabase::class.java, "todo_database")
                .build()
    }

}