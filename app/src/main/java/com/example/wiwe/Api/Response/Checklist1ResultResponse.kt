package com.example.wiwe.Api.Response

data class Checklist1ResultResponse(

    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: Checklist1ResponseResult1?
)

data class Checklist1ResponseResult1(
    val data:Checklist1Result1?,
    val message: String
)

data class Checklist1Result1(

    var checklistResult: String,
)

