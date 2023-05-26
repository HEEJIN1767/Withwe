package com.example.wiwe.Api.Response

import java.time.LocalDateTime

data class ReviewCreateResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: commentResult

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class commentResult(
    val data:commentcreate,
    val message: String
)
//댓글 작성
data class commentcreate(

    var commentId: Long,
    var commentContent: String,
    var commentWriter:String,
    var commentCreatedAt: String

)
