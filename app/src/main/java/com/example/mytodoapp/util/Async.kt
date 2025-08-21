package com.example.mytodoapp.util

sealed class Async<out T> {

    object Loading : Async<Nothing>()

    data class Success<out T>(val data: T): Async<T>()

    data class Error(val message: String): Async<Nothing>()

}