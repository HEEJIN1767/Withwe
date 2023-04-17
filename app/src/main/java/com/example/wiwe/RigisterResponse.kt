package com.example.wiwe

data class RigisterResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: RegisterResult?

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class RegisterResult(
    val message: String
)