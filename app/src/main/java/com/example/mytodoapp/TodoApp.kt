package com.example.mytodoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class TodoApp: Application() {

    companion object {
        const val TAG = "TA - "
    }

    override fun onCreate() {
        super.onCreate()
        plant(object: DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, "${TAG}${tag}", message, t)
            }

            override fun createStackElementTag(element: StackTraceElement): String? {
                return String.format(
                    "%s:%s:%s",
                    element.methodName,
                    element.lineNumber,
                    super.createStackElementTag(element)
                )
            }
        })
    }
}