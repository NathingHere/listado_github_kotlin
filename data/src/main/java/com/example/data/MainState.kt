package com.example.data

sealed class MainState {
    object Empty : MainState()
    data class DrawItems(val items: List<Repository>) : MainState()
    data class ShowErrorMessage(val message: String) : MainState()
}

