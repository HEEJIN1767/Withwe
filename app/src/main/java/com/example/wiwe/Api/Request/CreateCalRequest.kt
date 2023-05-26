package com.example.wiwe.Api.Request

import java.time.LocalDate

data class CreateCalRequest(
    val memoTitle: String,
    val memoContent1: String,
    val memoContent2: String,
    val memoDate: String
)