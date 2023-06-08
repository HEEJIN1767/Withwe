package com.example.wiwe.Api.Response

data class MyreviewResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: myreviewResult

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class myreviewResult(
    val data:List<myreviewdata>,
    val message: String
)

data class myreviewdata(


    var commentId: Long,
    var commentContent:String,
    var commentWriter:String,
    var boardsId: Long,
    var commentCreatedAt: String
)