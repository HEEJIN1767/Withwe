package com.example.wiwe.Api.Request

data class ChangepassRequest(
    val recentPassword: String,
    val newPassword: String
)