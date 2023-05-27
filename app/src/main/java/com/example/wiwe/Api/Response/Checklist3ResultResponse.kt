package com.example.wiwe.Api.Response

data class Checklist3ResultResponse(

    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: Checklist3ResponseResult1?
)

data class Checklist3ResponseResult1(
    val data:Checklist3Result1?,
    val message: String
)

data class Checklist3Result1(

    var checklistResult: String,
)
