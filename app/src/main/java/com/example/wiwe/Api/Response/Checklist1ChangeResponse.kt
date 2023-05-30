package com.example.wiwe.Api.Response

data class Checklist1ChangeResponse(

    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: Checklist1ChangeResult?
)

data class Checklist1ChangeResult(
    val data:Checklist1Result?,
    val message: String
)

data class Checklist1Result(

    var username: String,
    var name:String,
    var nickname: String,
)
