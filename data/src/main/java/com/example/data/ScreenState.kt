package com.example.data

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    data class Render<T>(val renderState: T) : ScreenState<T>()
}