package com.example.wiwe.Api.Request

public final data class UserRegisterRequest(
    val username: String,
    val password: String,
    val name: String,
    val nickname: String
)