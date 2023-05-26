package com.example.wiwe.Api.Response

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class CommentGetListResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: getreviewresult

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class getreviewresult(

    val data: List<getreview>,
    val message: String
)

data class getreview(

    var commentId: Long,
    var commentContent: String,
    var commentWriter:String,
    var commentCreatedAt: String

)



