package com.example.wiwe.Api.Request

data class ReviewCreateRequest(
    val boardsId: Long,
    val commentContent: String
)