package com.example.mytodoapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TodoApp: Application() {

    companion object {
        const val TAG = "TodoApp"
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "TodoApp created")
    }
}