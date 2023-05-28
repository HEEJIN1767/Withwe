package com.example.wiwe.Api.Response

data class ChecklistFinalResultResponse(

    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: ChecklistFinalResponseResult?
)

data class ChecklistFinalResponseResult(
    val data:ChecklistFinalResult?,
    val message: String
)

data class ChecklistFinalResult(

    var userNickname: String,
    var result: String,
    var result2: String,
    var result3: String
)
