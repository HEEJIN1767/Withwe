package com.example.wiwe.Api.Response

data class Checklist2ResultResponse(

    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: Checklist2ResponseResult1?
)

data class Checklist2ResponseResult1(
    val data:Checklist1Result2?,
    val message: String
)

data class Checklist1Result2(

    var checklistResult: String,
    var checklistNumber: String
)
