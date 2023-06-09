package com.example.ltech.utils

sealed class LoginState {
    object Success : LoginState()
    object Denied : LoginState()
    data class Error(val message: String) : LoginState()
    object Empty : LoginState()
}