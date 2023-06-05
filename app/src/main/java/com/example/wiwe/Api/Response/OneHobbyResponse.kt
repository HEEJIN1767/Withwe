package com.example.wiwe.Api.Response

data class OneHobbyResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: OneHobbyResult?

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class OneHobbyResult(
    val data:OneHobbyData,
    val message: String
)

data class OneHobbyData(


    var hobbyId: Long,
    var hobbyTitle:String,
    var hobbyAttribute1: String,
    var hobbyAttribute2: String,
    var hobbyAttribute3: String,
    var hobbyContent: String

)