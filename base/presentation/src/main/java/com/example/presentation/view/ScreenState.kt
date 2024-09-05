package com.example.presentation.view

sealed class ScreenState {
    object Loading : ScreenState()

    object Success : ScreenState()
}
