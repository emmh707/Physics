package com.example.innovatevr.models

data class UserModel(
    val fullName: String,
    var email: String,
    var password: String,
    val confirmPassword: String,
    var userId: String = "",
)
