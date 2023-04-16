package com.example.wiwe

import com.google.gson.annotations.SerializedName

//아웃풋을 만든다
//아웃풋=서버에서 통신을 호출했을 때 받아오는 응답값
data class LoginResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: LoginResult

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class LoginResult(
    val data: Login,
    val message: String
        )

data class Login(
    val accessToken: String
)


